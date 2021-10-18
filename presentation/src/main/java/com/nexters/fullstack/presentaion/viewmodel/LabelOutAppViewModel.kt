package com.nexters.fullstack.presentaion.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.domain.entity.LabelEntity
import com.nexters.fullstack.presentaion.BaseViewModel
import com.nexters.fullstack.presentaion.model.Label
import com.nexters.fullstack.domain.usecase.GetLabels
import kotlinx.coroutines.launch

class LabelOutAppViewModel(
    private val loadLabelUseCase: GetLabels
) : BaseViewModel() {
    private val state: State = State()
    private val myLabelList = mutableListOf<LabelEntity>()
    private val selectedLabelList = mutableListOf<LabelEntity>()
    private val recentlySearchList = mutableListOf<LabelEntity>()
    private val searchResultList = mutableListOf<LabelEntity>()

    fun state(): State = state

    fun loadImage(uri: Uri) {
        state.imageUri.value = uri
    }

    fun selectLabel(name: String) {
        for (label in myLabelList) {
            if (label.text == name) {
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
            if (label.text.contains(state.searchKeyword.value!!)) {
                searchResultList.add(label)
            }
        }
        state.searchResult.value = searchResultList

        return searchResultList.size != 0
    }

    init {

        // TODO init my label list using usecase
        myLabelList.add(LabelEntity(1,"label1", "Yellow"))
        myLabelList.add(LabelEntity(2,"label2", "Red"))
        myLabelList.add(LabelEntity(3,"label3", "Pink"))
        myLabelList.add(LabelEntity(4,"label4", "Purple Blue"))
        myLabelList.add(LabelEntity(5,"label5", "Green"))
        myLabelList.add(LabelEntity(6,"label6", "Gray"))
        myLabelList.add(LabelEntity(7,"label7", "Orange"))

        recentlySearchList.add(LabelEntity(1,"label1", "Yellow"))
        recentlySearchList.add(LabelEntity(2,"label2", "Red"))
        recentlySearchList.add(LabelEntity(3,"label3", "Pink"))
        recentlySearchList.add(LabelEntity(4,"label4", "Purple Blue"))
        recentlySearchList.add(LabelEntity(5,"label5", "Green"))

        state.myLabels.value = myLabelList
        state.recentlySearch.value = recentlySearchList
    }

    data class State(
        val imageUri: MutableLiveData<Uri> = MutableLiveData(),
        val myLabels: MutableLiveData<List<LabelEntity>> = MutableLiveData(),
        val selectedLabels: MutableLiveData<List<LabelEntity>> = MutableLiveData(),
        val searchKeyword: MutableLiveData<String> = MutableLiveData(),
        val searchResult: MutableLiveData<List<LabelEntity>> = MutableLiveData(),
        val recentlySearch: MutableLiveData<List<LabelEntity>> = MutableLiveData(),
        val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState.MY_LABEL)
    )

    enum class ViewState {
        NO_LABEL,
        MY_LABEL,
        RECENT_LABEL,
        SEARCH_RESULT,
        NO_RESULT
    }
}