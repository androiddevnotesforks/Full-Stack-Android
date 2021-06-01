package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.Label

class HomeSearchRecommendViewModel : BaseViewModel() {
    private val state = State()

    fun state() = state

    data class State(
        val selectedLabel : MutableLiveData<List<Label>> = MutableLiveData()
    )
}