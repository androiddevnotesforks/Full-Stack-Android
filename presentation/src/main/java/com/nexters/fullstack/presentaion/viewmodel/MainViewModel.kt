package com.nexters.fullstack.presentaion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.domain.Mapper
import com.nexters.fullstack.domain.entity.LabellingState
import com.nexters.fullstack.presentaion.model.*
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.usecase.GetUnlabeledImages
import com.nexters.fullstack.domain.usecase.LoadImageUseCase
import com.nexters.fullstack.domain.usecase.base.BaseUseCase
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(
    getUnlabeledImages: GetUnlabeledImages,
    localImageDBUseCase: LoadImageUseCase,
    mapper: Mapper<FileImageEntity, FileImageViewData>
) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val flipStateSubject = PublishSubject.create<LabellingState>()
    private val onClickButton = PublishSubject.create<MainLabelState>()
    private val mainLabel = MutableLiveData<MainLabel>()
    private val startLabeling = MutableLiveData<Unit>()
    private val _localImage = MutableLiveData<List<ImageEntity>>()
    private val _imageItemCount = MutableLiveData<Int>()


    val input = object : MainInput {
        override fun onClickedButton(state: MainLabelState) = onClickButton.onNext(state)

        override fun emitLabellingState(state: LabellingState) = flipStateSubject.onNext(state)
    }

    val output = object : MainOutput {
        override fun getLabelList(): LiveData<MainLabel> {
            return mainLabel
        }

        override fun getLocalImageCount(): LiveData<Int> = _imageItemCount

        override fun startLabelling() = startLabeling
    }

    init {
        val images: Single<List<FileImageViewData>> =
            getUnlabeledImages.buildUseCase(SCREEN_SHOT_PRIFIX)
                .map {
                    it.map { localLabel ->
                        mapper.toData(localLabel)
                    }
                }.cache()

        val state = onClickButton.cache()

        val localImage: Maybe<List<ImageEntity>> =
            localImageDBUseCase.buildUseCase(Unit)
                .cache()

        disposable.addAll(
            images
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    mainLabel.value = MainLabel(images = response)
                    _imageItemCount.value = response.size
                }, {}),

            localImage
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _localImage.value = it
                }, {}),

            Observable.combineLatest(
                images.toObservable(),
                state,
                ::MainLabel
            ).subscribe({ response ->
                if (response.state == MainLabelState.Approve) {
                    startLabeling.value = Unit
                }
                mainLabel.value = response
            }, {
                it.printStackTrace()
            })
        )
    }




    interface MainInput : Input {
        fun onClickedButton(state: MainLabelState)
        fun emitLabellingState(state: LabellingState)
    }

    interface MainOutput : Output {
        fun getLabelList(): LiveData<MainLabel>

        fun getLocalImageCount(): LiveData<Int>

        //Router
        fun startLabelling(): LiveData<Unit>
    }

    companion object {
        private const val SCREEN_SHOT_PRIFIX = "Screenshots"
    }
}