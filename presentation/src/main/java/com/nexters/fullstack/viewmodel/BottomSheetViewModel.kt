package com.nexters.fullstack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.bottomsheet.BottomSheetItem

class BottomSheetViewModel : BaseViewModel() {
    private val _items = MutableLiveData<List<BottomSheetItem>>()

    val items: LiveData<List<BottomSheetItem>>
        get() = _items


    init {
        _items.value = listOf(BottomSheetItem("라벨 수정하기"), BottomSheetItem("라벨 삭제하기"))
    }
}