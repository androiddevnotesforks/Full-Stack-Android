package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.source.ViewState
import com.tsdev.feature.ui.data.PalletItem


class LabelingViewModel : BaseViewModel() {
    private val _viewState = MutableLiveData<ViewState>(ViewState.Selected)
    private val _colors = MutableLiveData(
        listOf(
            PalletItem("Yellow", "#E8C15D")
        )
    )

    val output = object : LabelingOutput {
        override fun viewState(): LiveData<ViewState> = _viewState
        override fun getLabels(): LiveData<List<PalletItem>> = _colors
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
        fun getLabels(): LiveData<List<PalletItem>>
    }

    interface LabelingInput : Input {
        fun setViewState(viewState: ViewState)
    }
}