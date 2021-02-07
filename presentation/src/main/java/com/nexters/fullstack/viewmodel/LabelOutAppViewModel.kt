package com.nexters.fullstack.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.Input
import com.nexters.fullstack.Output
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.Label
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LabelOutAppViewModel : BaseViewModel(){

    private val _imageUri : MutableLiveData<Uri> = MutableLiveData()
    val imageUri : LiveData<Uri> get() = _imageUri

    private val _myLabels : MutableLiveData<ArrayList<Label>> = MutableLiveData()
    val myLabels : LiveData<ArrayList<Label>> get() = _myLabels


    val input = object : LabelOutAppInput{
        override fun setImageUri(uri: Uri) {
            _imageUri.value = uri
        }

        override fun setMyLabel() {
            val array = ArrayList<Label>()
            viewModelScope.launch(Dispatchers.IO) {
                array.add(Label(Label.RECOMMEND, "test1"))
                array.add(Label(Label.DEFAULT,"test21111111"))
                array.add(Label(Label.DEFAULT,"test3"))
                array.add(Label(Label.DEFAULT,"te3"))
                array.add(Label(Label.DEFAULT,"test3"))
                array.add(Label(Label.DEFAULT,"test11111113"))
                array.add(Label(Label.DEFAULT,"test3"))
            }
            _myLabels.value = array
        }
    }

    val output = object : LabelOutAppOutput {
    }

    interface LabelOutAppInput : Input{
        // set state
        fun setImageUri(uri: Uri)
        fun setMyLabel()
    }

    interface LabelOutAppOutput : Output{
    }

    init {
        input.setMyLabel()
    }
}