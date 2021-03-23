package com.nexters.fullstack.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.mapper.LabelSourceMapper
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.usecase.GetLabelManagementUseCase
import kotlinx.coroutines.launch

class LabelOutAppViewModel(
    private val loadLabelUseCase: GetLabelManagementUseCase
) : BaseViewModel() {
    private val state: State
    private val myLabelList = mutableListOf<LabelSource>()
    private val selectedLabelList = mutableListOf<LabelSource>()
    private val recentlySearchList = mutableListOf<LabelSource>()
    private val searchResultList = mutableListOf<LabelSource>()

    fun state(): State = state

    fun loadImage(uri: Uri) {
        state.imageUri.value = uri
    }

    fun selectLabel(name: String) {
        for (label in myLabelList) {
            if (label.name == name) {
                myLabelList.remove(label)
                selectedLabelList.add(0, label)
                break
            }
        }
        state.myLabels.value = myLabelList
        state.selectedLabels.value = selectedLabelList
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

    fun setViewState(viewState: ViewState) {
        state.viewState.value = viewState
    }

    fun clearSearchKeyword() {
        state.searchKeyword.value = ""
    }

    // TODO modify to using usecase
    fun search(): Boolean {
        searchResultList.clear()
        for (label in myLabelList) {
            if (label.name.contains(state.searchKeyword.value!!)) {
                searchResultList.add(label)
            }
        }
        state.searchResult.value = searchResultList

        return searchResultList.size != 0
    }

    init {
        state = State()

        // TODO init my label list using usecase
        viewModelScope.launch {
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Yellow", "인테리어"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Green", "OOTD"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Orange", "컬러 팔레트"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Red", "UI 레퍼런스"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Pink", "편집디자인"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Violet", "채팅"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Purple Blue", "memo모음"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Blue", "글귀"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Peacock Green", "장소(공연,전시 등)"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Green", "영화"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Gray", "네일"))
            myLabelList.add(LabelSource(LabelSource.DEFAULT, "Purple Blue", "맛집"))
        }
        state.myLabels.value = myLabelList

        viewModelScope.launch {
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "Yellow", "OOTD"))
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "Red", "UI 레퍼런스"))
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "Violet", "채팅"))
            recentlySearchList.add(LabelSource(LabelSource.DEFAULT, "Yellow", "인테리어"))
        }
        state.recentlySearch.value = recentlySearchList

//        viewModelScope.launch {
//            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
//            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
//            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
//            searchResultList.add(LabelSource(LabelSource.DEFAULT, "", "search_label1"))
//        }
//        state.searchResult.value = searchResultList
    }

    data class State(
        val imageUri: MutableLiveData<Uri> = MutableLiveData(),
        val myLabels: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val selectedLabels: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val searchKeyword: MutableLiveData<String> = MutableLiveData(),
        val searchResult: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val recentlySearch: MutableLiveData<List<LabelSource>> = MutableLiveData(),
        val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState.MY_LABEL)
    )

    // TODO refactor : state가 list를 가지고 있어서 activity에서 분기 처리 안하고 일괄적으로 처리해줄 수 있도록 ?
    enum class ViewState {
        MY_LABEL,
        RECENT_LABEL,
        SEARCH_RESULT,
        NO_RESULT
    }
}