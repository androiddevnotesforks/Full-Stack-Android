package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel

class AlbumViewModel : BaseViewModel() {

    private val _label = MutableLiveData<String>()


    val input = object : Input {
        override fun setLabelName(name: String) {
            _label.value = name
        }
    }

    val output = object : Output {
        override fun getLabelName(): LiveData<String> = _label
    }

    interface Input {
        fun setLabelName(name: String)
    }

    interface Output {
        fun getLabelName(): LiveData<String>
    }
}