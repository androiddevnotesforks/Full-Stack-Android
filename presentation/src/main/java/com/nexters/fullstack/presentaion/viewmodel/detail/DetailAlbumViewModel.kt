package com.nexters.fullstack.presentaion.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.presentaion.BaseViewModel
import com.nexters.fullstack.domain.local.LabelaryLocalDataSource
import com.nexters.fullstack.domain.usecase.DeleteImageUseCase
import com.nexters.fullstack.domain.usecase.GetDetailImage
import com.nexters.fullstack.domain.usecase.PostBookmarkingImageUseCase
import com.nexters.fullstack.domain.usecase.RequestBookmarkingImages
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import com.nexters.fullstack.presentaion.printSt
import com.nexters.fullstack.util.SingleLiveData
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailAlbumViewModel constructor(
    private val getLocalFindImageUseCase: GetDetailImage,
    private val postUpdateLocalImageUseCase: PostBookmarkingImageUseCase,
    private val deleteLocalImageUseCase: DeleteImageUseCase
) : BaseViewModel() {
    private val finish = SingleLiveData<Unit>()
    private val isContainImage = MutableLiveData(false)
    private val disposable = CompositeDisposable()
    private val progressBarState = SingleLiveData<ProgressState>()

    private val imageEntity = MutableLiveData<ImageEntity>()
    private val showDeleteDialog = SingleLiveData<DeleteDialogEntity>()

    val input = object : Input {
        override fun setIsContainImage(value: Boolean) {
            isContainImage.value = value
        }

        override fun setImageEntity(value: ImageEntity?) {
            imageEntity.value = value
        }

        override fun favorite(id: String) {
            showProgressBar()

            disposable.add(getLocalFindImageUseCase.buildUseCase(id)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map {
                    val updateEntity = it.imageEntity?.let { entity ->
                        entity.copy(liked = !entity.liked)
                    } ?: ImageEntity.empty()

                    PostBookmarkingImageUseCase.Request(
                        image = updateEntity
                    )
                }
                .flatMapCompletable(postUpdateLocalImageUseCase::buildUseCase)
                .andThen(getLocalFindImageUseCase.buildUseCase(id))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { dismissProgressBar() }
                .subscribe(
                    { setIsContainImage(it.imageEntity?.liked == true) },
                    (::printSt)
                )
            )
        }

        override fun share() {
            TODO("Not yet implemented")
        }

        override fun delete(imageEntity: ImageEntity) {
            showDeleteDialog.value = DeleteDialogEntity(
                title = "해당 사진을 삭제하시겠습니까?",
                positive = "삭제",
                onPositiveClickListener = {
                    showProgressBar()

                    disposable.add(
                        deleteLocalImageUseCase.buildUseCase(imageEntity)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doAfterTerminate { dismissProgressBar() }
                            .subscribe(
                                (::finish),
                                (::printSt)
                            )
                    )
                },
                negative = "취소",
                imageUrl = imageEntity.image.originUrl
            )

        }

        override fun finish() = finish.call()
    }

    val output = object : Output {
        override fun finish(): LiveData<Unit> = finish
        override fun getIsLocalContain(): LiveData<Boolean> = isContainImage
        override fun getImageEntity(): LiveData<ImageEntity> = imageEntity
        override fun showDeleteDialog(): LiveData<DeleteDialogEntity> = showDeleteDialog
    }

    interface Input {
        fun setIsContainImage(value: Boolean)
        fun setImageEntity(value: ImageEntity?)

        fun favorite(id: String)
        fun share()
        fun delete(imageEntity: ImageEntity)
        fun finish()
    }

    interface Output {
        fun finish(): LiveData<Unit>
        fun getIsLocalContain(): LiveData<Boolean>
        fun getImageEntity(): LiveData<ImageEntity>
        fun showDeleteDialog(): LiveData<DeleteDialogEntity>
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
            getLocalFindImageUseCase.buildUseCase(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn {
                    GetDetailImage.Result(
                        FileImageEntity.empty(),
                        ImageEntity.empty()
                    )
                }
                .doAfterTerminate { dismissProgressBar() }
                .subscribe(
                    {
                        input.setIsContainImage(it.imageEntity?.liked == true)
                        input.setImageEntity(it.imageEntity)
                    },
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

    data class DeleteDialogEntity(
        val title: String,
        val positive: String,
        val onPositiveClickListener: () -> Unit,
        val negative: String,
        val imageUrl: String
    )
}
