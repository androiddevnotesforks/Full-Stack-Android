package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.mapper.Mapper
import com.nexters.fullstack.source.*
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class MainViewModel(
    private val flipUseCase: BaseUseCase<LabellingState, Boolean>,
    albumLoadUseCase: BaseUseCase<String, Single<List<LocalImageDomain>>>,
    mapper: Mapper<LocalImageDomain, PresentLocalFile>
) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val flipStateSubject = PublishSubject.create<LabellingState>()
    private val onClickButton = PublishSubject.create<State>()

    private val mainLabel = MutableLiveData<MainLabel>()


    val input = object : MainInput {
        override fun onClickedButton(state: State) = onClickButton.onNext(state)

        override fun emitLabellingState(state: LabellingState) = flipStateSubject.onNext(state)
    }

    val output = object : MainOutput {
        override fun state(): LiveData<MainLabel> {
            return mainLabel
        }

        override fun startLabelling() = startLabeling
    }

    init {
        val images: Single<List<PresentLocalFile>> =
            albumLoadUseCase.buildUseCase(SCREEN_SHOT_PRIFIX)
                .map {
                    it.map { localLabel ->
                        mapper.toData(localLabel)
                    }
                }.cache()

        val state = onClickButton.cache()

        disposable.addAll(
            Observable.combineLatest(
                images.toObservable(),
                state,
                ::MainLabel
            ).subscribe({ response ->
                if (response.state == State.Approve) {
                    startLabeling.value = Unit
                }
                mainLabel.value = response
            }, {

            })
        )

//        disposable.add(
//            Observable.combineLatest(
//                images.toObservable(),
//                Observable.just(true),
//                ::State
//            ).subscribe(state::setValue)
//        )

//        disposable.add(
//            onClickImage.flatMapSingle {
//                images
//            }.subscribe()
//        )

//        disposable.add(albumLoadUseCase.buildUseCase(SCREEN_SHOT_PRIFIX)
//            .map {
//                it.map { localLabel ->
//                    mapper.toData(localLabel)
//                }
//            }
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ pathList ->
//                input.loadImages(pathList)
//            }, {
//                it.printStackTrace()
//            })
//        )

//        disposable.add(
//            flipStateSubject
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter { state -> state != LabellingState.Pending }
//                .subscribe { state ->
//                    val didStartLabelling = flipUseCase.buildUseCase(state)
//
//                    if (didStartLabelling) {
//                        startLabeling.value = Unit
//                    } else {
//                        //todo 첫 화면 맨 뒤로 이동.
//                    }
//                }
//        )
    }


    private val _images = MutableLiveData<List<PresentLocalFile>>()
    private val startLabeling = MutableLiveData<Unit>()

    interface MainInput : Input {
        //        fun loadImages(images: List<PresentLocalFile>)
        fun onClickedButton(state: State)
        fun emitLabellingState(state: LabellingState)
    }

    interface MainOutput : Output {
        fun state(): LiveData<MainLabel>

        //Router
        fun startLabelling(): LiveData<Unit>
    }

    companion object {
        private const val SCREEN_SHOT_PRIFIX = "Screenshots"
    }
}