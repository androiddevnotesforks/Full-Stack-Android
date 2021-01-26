package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.ViewState

class LabelingViewModel : BaseViewModel() {
    private val _viewState = MutableLiveData<ViewState>(ViewState.Selected)

    val viewState: LiveData<ViewState>
        get() = _viewState


    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState
    }


}