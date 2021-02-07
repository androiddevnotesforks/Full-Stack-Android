package com.nexters.fullstack.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.source.Label
import kotlinx.coroutines.launch

class LabelOutAppViewModel : BaseViewModel() {
    private lateinit var state : State
//    val myLabels = MutableLiveData<ArrayList<Label>>()
//    private val _selectedLabels: MutableLiveData<ArrayList<Label>> = MutableLiveData()
//    val selectedLabels: LiveData<ArrayList<Label>> get() = _selectedLabels

    val input = object : LabelOutAppInput {
        override fun loadImage(uri: Uri) {
            state.imageUri.value = uri
        }

//        override fun setMyLabel() {
//            val array = ArrayList<Label>()
//            viewModelScope.launch {
//                array.add(Label(Label.RECOMMEND, "test1"))
//                array.add(Label(Label.DEFAULT, "test21111111"))
//                array.add(Label(Label.DEFAULT, "test3"))
//                array.add(Label(Label.DEFAULT, "te3"))
//                array.add(Label(Label.DEFAULT, "test3"))
//                array.add(Label(Label.DEFAULT, "test11111113"))
//                array.add(Label(Label.DEFAULT, "test3"))
//            }
//            myLabels.value = array
//        }
    }

    val output = object : LabelOutAppOutput {

//        override fun selectLabel(selectedPosition: Int) {
//            val selectedLabel: Label = myLabels.value!![selectedPosition]
//            myLabels.value?.removeAt(selectedPosition)
//            //selectedLabels.value?.add(selectedLabel)
//            Log.e("selected item : ", selectedPosition.toString())
//        }

        override fun state(): State = state
    }

    interface LabelOutAppInput : Input {
        fun loadImage(uri: Uri)
    }

    interface LabelOutAppOutput : Output {
        fun state() : State
    }

    init {
        state = State()
    }

    data class State(
        // TODO image uri랑 my label, recommend label은 non null로 하고, init에서 state 정의할 때 받아와서 정의해주기 !
        val imageUri : MutableLiveData<Uri> = MutableLiveData(),
        val recommendedLabels: List<Label>? = null,
        val myLabels: List<Label>? = null,
        val selectedLabels : List<Label>? = null,
        val enableSearch : Boolean = false,
        val searchKeyword : String? = null
    )
}