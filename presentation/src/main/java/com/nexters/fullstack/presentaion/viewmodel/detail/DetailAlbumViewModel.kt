package com.nexters.fullstack.viewmodel.detail

import androidx.lifecycle.LiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.util.SingleLiveData

class DetailAlbumViewModel : BaseViewModel() {
    private val finish = SingleLiveData<Unit>()

    val input = object : Input {
        override fun finish() = finish.call()
    }

    val output = object : Output {
        override fun finish(): LiveData<Unit> = finish
    }

    interface Input {
        fun finish()
    }

    interface Output {
        fun finish(): LiveData<Unit>
    }
}
