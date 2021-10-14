package com.nexters.fullstack.presentaion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.domain.usecase.SearchImagesByLabel
import com.nexters.fullstack.domain.usecase.SearchImagesBySingleLabel
import com.nexters.fullstack.presentaion.printSt
import com.nexters.fullstack.util.SingleLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumViewModel constructor(
    private val getImagesByLabel: SearchImagesBySingleLabel
) : BaseViewModel() {

    private val _label = MutableLiveData<String>()
    private val _labelColor = MutableLiveData<String>()
    private val _images = MutableLiveData<List<ImageEntity>>()
    private val dispose = CompositeDisposable()


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
        override fun setImages(images: List<ImageEntity>) {
            _images.value = images
        }
    }

    val output = object : Output {
        override fun getLabelName(): LiveData<String> = _label
        override fun getLabelColor(): LiveData<String> = _labelColor
        override fun finishActivity() = _finish
        override fun getImages(): LiveData<List<ImageEntity>> = _images
    }

    interface Input {
        fun setLabelName(name: String)
        fun setLabelColor(color: String)
        fun goToFinish()
        fun setImages(images: List<ImageEntity>)
    }

    interface Output {
        fun getLabelName(): LiveData<String>
        fun getLabelColor(): LiveData<String>
        fun finishActivity(): LiveData<Unit>
        fun getImages(): LiveData<List<ImageEntity>>
    }

    fun fetchImages(label: LabelEntity) {
        dispose.add(
            getImagesByLabel.buildUseCase(label)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { input.setImages(it) },
                    ::printSt
                )
        )
    }

    override fun onCleared() {
        dispose.clear()
        super.onCleared()
    }
}