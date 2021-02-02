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
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val flipUseCase: BaseUseCase<LabellingState, Boolean>,
    private val albumLoadUseCase: BaseUseCase<String, Single<List<LocalLabelDomain>?>>,
    mapper: Mapper<LocalLabelDomain, PresentLocalFile>
) : BaseViewModel() {

    private val disposable = CompositeDisposable()

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
    }

    val input = object : MainInput {
        override fun loadImages(images: List<PresentLocalFile>) {
            _images.value = images
        }
    }

    val output = object : MainOutput {
        override fun images(): LiveData<List<PresentLocalFile>> {
            return _images
        }
    }

    private val _images = MutableLiveData<List<PresentLocalFile>>()
    private val _labellingState = MutableLiveData<LabellingState>(LabellingState.Pending)

    val labellingState: LiveData<LabellingState>
        get() = _labellingState

    fun setButtonState(labelState: LabellingState) {
        _labellingState.value = labelState
    }

    fun isLabellingStarted(labelState: LabellingState): Boolean {
        return flipUseCase.buildUseCase(labelState)
    }

    fun loadAlbumScreenShots(pathFilter: String): Single<List<LocalLabelDomain>?> {
        return albumLoadUseCase.buildUseCase(pathFilter)
    }

    interface MainInput : Input {
        fun loadImages(images: List<PresentLocalFile>)
    }

    interface MainOutput : Output {
        fun images(): LiveData<List<PresentLocalFile>>
    }

    companion object {
        private const val SCREEN_SHOT_PRIFIX = "Screenshots"
    }
}