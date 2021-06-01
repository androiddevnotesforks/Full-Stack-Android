package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel

class HomeSearchViewModel : BaseViewModel() {
    private val state = State()

    fun state() = state

    fun changeMode(){
        when(state.mode.value){
            ViewMode.RECOMMEND -> state.mode.value = ViewMode.SEARCH
            ViewMode.SEARCH -> state.mode.value = ViewMode.RECOMMEND
        }
    }

    data class State(
        val mode : MutableLiveData<ViewMode> = MutableLiveData(ViewMode.RECOMMEND)
    )

    enum class ViewMode{
        RECOMMEND, SEARCH
    }
}