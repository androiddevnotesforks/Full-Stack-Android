package com.nexters.fullstack.viewmodel

import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.source.ViewState


class LabelingViewModel : BaseViewModel() {
    private val _viewState = MutableLiveData<ViewState>(ViewState.Selected)
    val output = object : LabelingOutput {
        override fun viewState(): LiveData<ViewState> = _viewState
    }
    val input = object : LabelingInput {
        override fun setViewState(viewState: ViewState) {
            _viewState.value = viewState
        }
    }

    val viewState: LiveData<ViewState>
        get() = _viewState


    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }


    interface LabelingOutput : Output {
        fun viewState(): LiveData<ViewState>
    }

    interface LabelingInput : Input {
        fun setViewState(viewState: ViewState)
    }

}