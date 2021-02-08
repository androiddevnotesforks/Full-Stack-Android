package com.nexters.fullstack.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.Label
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LabelOutAppViewModel : BaseViewModel() {
    private val state: State
    private val myLabelList = mutableListOf<Label>()
    private val selectedLabelList = mutableListOf<Label>()


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

    fun completeLabeling(){
        // TODO usecase 연결 > image selectLabels
        viewModelScope.launch {

        }
    }

    init {
        state = State()

        // TODO replace to usecase
        viewModelScope.launch{
            myLabelList.add(Label(Label.DEFAULT, "label1"))
            myLabelList.add(Label(Label.DEFAULT, "label2"))
            myLabelList.add(Label(Label.DEFAULT, "label3"))
            myLabelList.add(Label(Label.DEFAULT, "label4"))
            myLabelList.add(Label(Label.DEFAULT, "label5"))
            myLabelList.add(Label(Label.DEFAULT, "label6"))
            myLabelList.add(Label(Label.DEFAULT, "label7"))
            myLabelList.add(Label(Label.DEFAULT, "label8"))
            myLabelList.add(Label(Label.DEFAULT, "label9"))
            myLabelList.add(Label(Label.DEFAULT, "label10"))
        }
        state.myLabels.value = myLabelList

    }

    data class State(
        val imageUri: MutableLiveData<Uri> = MutableLiveData(),
        val myLabels: MutableLiveData<List<Label>> = MutableLiveData(),
        val selectedLabels: MutableLiveData<List<Label>> = MutableLiveData()
    )
}