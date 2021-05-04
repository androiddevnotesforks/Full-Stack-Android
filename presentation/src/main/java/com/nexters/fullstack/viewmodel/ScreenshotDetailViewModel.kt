package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.HomeScreenshot

class ScreenshotDetailViewModel : BaseViewModel() {
    private val state: State = State()

    fun state(): State = state

    data class State(
        val image : MutableLiveData<HomeScreenshot> = MutableLiveData(),
        val mode : MutableLiveData<Mode> = MutableLiveData(Mode.INFO)
    )

    enum class Mode{
        FULL, INFO
    }
}