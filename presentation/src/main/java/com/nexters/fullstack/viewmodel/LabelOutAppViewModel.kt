package com.nexters.fullstack.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.LabelSource
import kotlinx.coroutines.launch

class LabelOutAppViewModel : BaseViewModel() {
    private val state: State
    private val myLabelList = mutableListOf<LabelSource>()
    private val selectedLabelList = mutableListOf<LabelSource>()


    fun state(): State = state

    fun loadImage(uri: Uri) {
        state.imageUri.value = uri
    }

    fun selectLabel(position: Int) {
        val selectedLabel = myLabelList[position]
        myLabelList.removeAt(position)
        selectedLabelList.add(0, selectedLabel)
        state.myLabels.value = myLabelList
        state.selectedLabels.value = selectedLabelList
    }

    fun deselectLabel(position: Int) {
        val selectedLabel = selectedLabelList[position]
        selectedLabelList.removeAt(position)
        myLabelList.add(selectedLabel)
        state.myLabels.value = myLabelList
        state.selectedLabels.value = selectedLabelList
    }

    fun completeLabeling() {
        // TODO usecase 연결 > image selectLabels
        viewModelScope.launch {

        }
    }

    init {
        state = State()

        // TODO replace to usecase
        viewModelScope.launch {
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label1"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label2"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label3"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label4"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label5"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label6"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label7"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label8"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label9"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label10"))
        }
        state.myLabels.value = myLabelList

    }

    data class State(
        val imageUri: MutableLiveData<Uri> = MutableLiveData(),
        val myLabels: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val selectedLabels: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val searchKeyword : MutableLiveData<String> = MutableLiveData(),
        val searchResult : MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val recentlySearch : MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val isSearchMode : MutableLiveData<Boolean> = MutableLiveData(false)
    )
}