package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.Screenshot

class HomeScreenshotViewModel : BaseViewModel() {
    private val state = State()

    fun state() = state

    fun changeMode(){
        when(state.mode.value){
            Mode.DEFAULT -> state.mode.value = Mode.SELECTION
            Mode.SELECTION -> state.mode.value = Mode.DEFAULT
        }
    }

    fun updateSelected(updatedList : ArrayList<Int>){
        state.selectedImages.value = updatedList
        // TODO  개수 오류 파악하기
//        state.selectedTitle.value = state.selectedImages.value?.let{
//            it.size.toString() + "개"
//        } ?: ""
    }

    data class State(
        val title : MutableLiveData<String> = MutableLiveData(),
        val images : MutableLiveData<List<Screenshot>> = MutableLiveData(),
        val mode : MutableLiveData<Mode> = MutableLiveData(Mode.DEFAULT),
        val selectedImages : MutableLiveData<ArrayList<Int>> = MutableLiveData(),
        val selectedTitle : MutableLiveData<String> = MutableLiveData("")
    )

    enum class Mode{
        DEFAULT, SELECTION
    }
}