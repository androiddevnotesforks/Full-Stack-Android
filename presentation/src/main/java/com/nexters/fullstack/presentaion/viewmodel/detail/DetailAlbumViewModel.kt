package com.nexters.fullstack.presentaion.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.presentaion.BaseViewModel
import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.presentaion.printSt
import com.nexters.fullstack.util.SingleLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailAlbumViewModel constructor(
    private val getLocalImages: LabelaryLocalDataSource.Image
) : BaseViewModel() {
    private val finish = SingleLiveData<Unit>()
    private val isContainImage = MutableLiveData(false)
    private val disposable = CompositeDisposable()
    private val progressBarState = SingleLiveData<ProgressState>()

    val input = object : Input {
        override fun setIsContainImage(value: Boolean) {
            isContainImage.value = value
        }

        override fun favorite(id: String) {
            showProgressBar()

            disposable.add(getLocalImages.find(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map {
                    val updateEntity = it.copy(liked = !it.liked)

                    updateEntity
                }
                .flatMapCompletable(getLocalImages::insertOrUpdate)
                .andThen(getLocalImages.find(id))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { dismissProgressBar() }
                .subscribe(
                    { setIsContainImage(it.liked) },
                    (::printSt)
                )
            )
        }

        override fun share() {
            TODO("Not yet implemented")
        }

        override fun delete(imageEntity: ImageEntity) {
            disposable.add(
                getLocalImages.delete(imageEntity)
                    .doAfterTerminate { }
                    .subscribe(
                        (::finish),
                        (::printSt)
                    )
            )
        }

        override fun finish() = finish.call()
    }

    val output = object : Output {
        override fun finish(): LiveData<Unit> = finish
        override fun getIsLocalContain(): LiveData<Boolean> = isContainImage
    }

    interface Input {
        fun setIsContainImage(value: Boolean)

        fun favorite(id: String)
        fun share()
        fun delete(imageEntity: ImageEntity)
        fun finish()
    }

    interface Output {
        fun finish(): LiveData<Unit>
        fun getIsLocalContain(): LiveData<Boolean>
    }

    private fun showProgressBar() {
        progressBarState.value = ProgressState.SHOW
    }

    private fun dismissProgressBar() {
        progressBarState.value = ProgressState.DISMISS
    }

    fun fetchImage(id: String) {
        showProgressBar()

        disposable.add(
            getLocalImages.find(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn { ImageEntity.empty() }
                .doAfterTerminate { dismissProgressBar() }
                .subscribe(
                    { input.setIsContainImage(it.liked) },
                    (::printSt)
                )
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    enum class ProgressState {
        SHOW, DISMISS
    }
}
