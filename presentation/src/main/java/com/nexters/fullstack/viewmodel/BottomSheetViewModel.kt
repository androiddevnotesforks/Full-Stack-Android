package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.bottomsheet.BottomSheetItem

class BottomSheetViewModel : BaseViewModel() {
    private val _items = MutableLiveData<List<BottomSheetItem>>()
}