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
            PalletItem("Yellow", "#353125", "#E8C15D","#FFE299"),
            PalletItem("Orange", "#2E2218", "#EC9147","#FFCBA1"),
            PalletItem("Red", "#2C1922", "#C76761","#FFA799"),
            PalletItem("Pink", "#2D1D25", "#E089B5","#FFC7E3"),
            PalletItem("Violet", "#2A1F38", "#A06EE5","#D9C2FF"),
            PalletItem("Cobalt Blue", "#2B2B4D", "#6565E5","#BFBFFF"),
            PalletItem("Blue", "#132334", "#4CA6FF","#B2D9FF"),
            PalletItem("Peacock Green", "#182424", "#52CCCC","#A1E5E5"),
            PalletItem("Green", "#1D2A24","#3EA87A" ,"#B1E5CF"),
            PalletItem("Gray", "#282A2F", "#7B8399","#CCDAFF")
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