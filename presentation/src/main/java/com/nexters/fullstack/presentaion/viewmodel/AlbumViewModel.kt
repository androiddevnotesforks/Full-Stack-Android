package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.util.SingleLiveData

class AlbumViewModel : BaseViewModel() {

    private val _label = MutableLiveData<String>()
    private val _labelColor = MutableLiveData<String>()
    private val _images = MutableLiveData<List<FileImageEntity>>()

    //route
    private val _finish = SingleLiveData<Unit>()

    val input = object : Input {
        override fun setLabelName(name: String) {
            _label.value = name
        }

        override fun setLabelColor(color: String) {
            _labelColor.value = color
        }

        override fun goToFinish() = _finish.call()
        override fun setImages(images: List<FileImageEntity>) {
            _images.value = images
        }
    }

    val output = object : Output {
        override fun getLabelName(): LiveData<String> = _label
        override fun getLabelColor(): LiveData<String> = _labelColor
        override fun finishActivity() = _finish
        override fun getImages(): LiveData<List<FileImageEntity>> = _images
    }

    interface Input {
        fun setLabelName(name: String)
        fun setLabelColor(color: String)
        fun goToFinish()
        fun setImages(images: List<FileImageEntity>)
    }

    interface Output {
        fun getLabelName(): LiveData<String>
        fun getLabelColor(): LiveData<String>
        fun finishActivity(): LiveData<Unit>
        fun getImages(): LiveData<List<FileImageEntity>>
    }
}