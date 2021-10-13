package com.nexters.fullstack.presentaion.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.presentaion.BaseViewModel
import com.nexters.fullstack.presentaion.model.Label
import com.nexters.fullstack.domain.usecase.GetLabels
import kotlinx.coroutines.launch

class LabelOutAppViewModel(
    private val loadLabelUseCase: GetLabels
) : BaseViewModel() {
    private val state: State = State()
    private val myLabelList = mutableListOf<Label>()
    private val selectedLabelList = mutableListOf<Label>()
    private val recentlySearchList = mutableListOf<Label>()
    private val searchResultList = mutableListOf<Label>()

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

        // TODO init my label list using usecase
        myLabelList.add(Label("labe233232l1", "Yellow"))
        myLabelList.add(Label("label2", "Red"))
        myLabelList.add(Label("lab232el3", "Pink"))
        myLabelList.add(Label("label4", "Purple Blue"))
        myLabelList.add(Label("label2235", "Green"))
        myLabelList.add(Label("label6", "Gray"))
        myLabelList.add(Label("label7", "Orange"))

        recentlySearchList.add(Label("label1", "Yellow"))
        recentlySearchList.add(Label("label2", "Red"))
        recentlySearchList.add(Label("label3", "Pink"))
        recentlySearchList.add(Label("label4", "Purple Blue"))
        recentlySearchList.add(Label("label5", "Green"))

        state.myLabels.value = myLabelList
        state.recentlySearch.value = recentlySearchList
    }

    data class State(
        val imageUri: MutableLiveData<Uri> = MutableLiveData(),
        val myLabels: MutableLiveData<List<Label>> = MutableLiveData(),
        val selectedLabels: MutableLiveData<List<Label>> = MutableLiveData(),
        val searchKeyword: MutableLiveData<String> = MutableLiveData(),
        val searchResult: MutableLiveData<List<Label>> = MutableLiveData(),
        val recentlySearch: MutableLiveData<List<Label>> = MutableLiveData(),
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