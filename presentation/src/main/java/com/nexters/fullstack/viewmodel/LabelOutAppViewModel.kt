package com.nexters.fullstack.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.LabelSource
import kotlinx.coroutines.launch

class LabelOutAppViewModel : BaseViewModel() {
    private val state: State
    private val myLabelList = mutableListOf<LabelSource>()
    private val selectedLabelList = mutableListOf<LabelSource>()
    private val recentlySearchList = mutableListOf<LabelSource>()
    private val searchResultList = mutableListOf<LabelSource>()

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

    fun completeLabeling() {
        // TODO usecase 연결 > image selectLabels
        viewModelScope.launch {

        }
    }

    fun setViewState(viewState: ViewState){
        state.viewState.value = viewState
    }

    fun clearSearchKeyword(){
        state.searchKeyword.value = ""
    }

    init {
        state = State()

        // TODO init my label list using usecase
        viewModelScope.launch {
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "my_label1"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label2"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label3"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label4"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label5"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label6"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label7"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label8"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label9"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "", "label10"))
        }
        state.myLabels.value = myLabelList

        viewModelScope.launch {
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "", "recent_label1"))
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "", "recent_label1"))
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "", "recent_label1"))
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "", "recent_label1"))
        }
        state.recentlySearch.value = recentlySearchList

        viewModelScope.launch {
            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
        }
        state.searchResult.value = searchResultList
    }

    data class State(
        val imageUri: MutableLiveData<Uri> = MutableLiveData(),
        val myLabels: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val selectedLabels: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val searchKeyword : MutableLiveData<String> = MutableLiveData(),
        val searchResult : MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val recentlySearch : MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val viewState : MutableLiveData<ViewState> = MutableLiveData(ViewState.MY_LABEL)
    )

    // TODO refactor : state가 list를 가지고 있어서 activity에서 분기 처리 안하고 일괄적으로 처리해줄 수 있도록 ?
    enum class ViewState{
        MY_LABEL,
        RECENT_LABEL,
        SEARCH_RESULT
    }
}