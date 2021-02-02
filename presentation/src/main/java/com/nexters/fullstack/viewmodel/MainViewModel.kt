package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.mapper.Mapper
import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.source.PresentLocalFile
import com.nexters.fullstack.source.data.LocalLabelDomain
import com.nexters.fullstack.usecase.base.BaseUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(
    private val flipUseCase: BaseUseCase<LabellingState, Boolean>,
    albumLoadUseCase: BaseUseCase<String, Single<List<LocalLabelDomain>?>>,
    mapper: Mapper<LocalLabelDomain, PresentLocalFile>
) : BaseViewModel() {

    private val disposable = CompositeDisposable()
    private val flipStateSubject = PublishSubject.create<LabellingState>()

    init {
        disposable.add(albumLoadUseCase.buildUseCase(SCREEN_SHOT_PRIFIX)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.map { localLabel ->
                    mapper.toData(localLabel)
                }
            }
            .subscribe({ pathList ->
                input.loadImages(pathList)
            }, {
                it.printStackTrace()
            })
        )

        disposable.add(
            flipStateSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { state -> state != LabellingState.Pending }
                .subscribe { state ->
                    val didStartLabelling = flipUseCase.buildUseCase(state)

                    if (didStartLabelling) {
                        startLabeling.value = Unit
                    } else {
                        //todo 첫 화면 맨 뒤로 이동.
                    }
                }
        )
    }

    val input = object : MainInput {
        override fun loadImages(images: List<PresentLocalFile>) {
            _images.value = images
        }

        override fun emitLabellingState(state: LabellingState) = flipStateSubject.onNext(state)
    }

    val output = object : MainOutput {
        override fun images(): LiveData<List<PresentLocalFile>> {
            return _images
        }

        override fun startLabelling() = startLabeling
    }

    private val _images = MutableLiveData<List<PresentLocalFile>>()
    private val startLabeling = MutableLiveData<Unit>()

    interface MainInput : Input {
        fun loadImages(images: List<PresentLocalFile>)
        fun emitLabellingState(state: LabellingState)
    }

    interface MainOutput : Output {
        fun images(): LiveData<List<PresentLocalFile>>
        fun startLabelling(): LiveData<Unit>
    }

    companion object {
        private const val SCREEN_SHOT_PRIFIX = "Screenshots"
    }
}