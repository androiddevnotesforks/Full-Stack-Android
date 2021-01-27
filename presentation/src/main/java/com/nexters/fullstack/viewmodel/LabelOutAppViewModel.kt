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

class LabelOutAppViewModel : BaseViewModel(){

    interface LabelOutAppInput : Input {
        fun clickCancel()
        fun clickDone(labels : List<Label>)
        fun selectLabel(label : Label)
        fun unSelectLabel(label : Label)
        fun enableSearch()
        fun disableSearch()
        fun searchKeyword(keyword : String)
        // TODO search는 타이핑 할 때마다 ?
        fun search()
    }

    interface LabelOutAppOutput : Output {
        fun state() : LiveData<LabelOutAppState>
        fun cancelLabeling()
        // TODO 보기 클릭시에 어떤 화면으로 진입되는지 ?
        fun successLabeling()
        fun selectLabel() : LiveData<Pair<List<Label>, List<Label>>>
        fun showSuccessToast() : LiveData<String>
        fun showSearchList() : LiveData<Label>?
        fun goAddLabelActivity() : LiveData<String>
    }

    data class LabelOutAppState (
        val imageUri : Uri,
        val selectedLabels : List<Label>,
        val myLabels : List<Label>,
        val enableSearch : Boolean,
        val searchKeyword : String,
        val searchResult : Label?
    )
}