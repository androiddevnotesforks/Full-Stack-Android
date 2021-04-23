package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.HomeScreenshot

class HomeScreenshotViewModel : BaseViewModel() {
    private val state = State()

    fun state() = state

    data class State(
        val title : MutableLiveData<String> = MutableLiveData(),
        val images : MutableLiveData<List<HomeScreenshot>> = MutableLiveData()
    )
}