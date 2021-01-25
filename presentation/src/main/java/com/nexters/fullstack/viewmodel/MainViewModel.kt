package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.usecase.FlipUseCase

class MainViewModel(private val flipUseCase: FlipUseCase) : BaseViewModel() {

    private val _labellingState = MutableLiveData<LabellingState>(LabellingState.Pending)

    val labellingState: LiveData<LabellingState>
        get() = _labellingState


    fun setButtonState(labelState: LabellingState) {
        _labellingState.value = labelState
    }

    fun isLabellingStart(labelState: LabellingState): Boolean {
        return flipUseCase.buildUseCase(labelState)
    }

}