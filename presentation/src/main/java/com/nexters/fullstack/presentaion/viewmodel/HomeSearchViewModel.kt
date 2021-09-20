package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.presentaion.source.Label

class HomeSearchViewModel : BaseViewModel() {
    private val state = State()

    private val selectedLabelList = mutableListOf<Label>()
    private val myLabelList = mutableListOf<Label>()
    private val recentlySearchList = mutableListOf<Label>()
    private val searchResultList = mutableListOf<Label>()

    init {
        // TODO : change for using cache data
        myLabelList.add(Label("label1", "Yellow"))
        myLabelList.add(Label("label2", "Red"))
        myLabelList.add(Label("label3", "Pink"))
        myLabelList.add(Label("label4", "Purple Blue"))
        myLabelList.add(Label("label5", "Green"))
        myLabelList.add(Label("label6", "Gray"))
        myLabelList.add(Label("label7", "Orange"))

        recentlySearchList.add(Label("label1", "Yellow"))
        recentlySearchList.add(Label("label2", "Red"))
        recentlySearchList.add(Label("label3", "Pink"))
        recentlySearchList.add(Label("label4", "Purple Blue"))
        recentlySearchList.add(Label("label5", "Green"))

        state.myLabel.value = myLabelList
        state.recentlySearchLabel.value = recentlySearchList
    }

    fun state() = state

    fun setSearchMode(){
        state.mode.value = ViewMode.SEARCH
    }

    fun setRecommendMode(){
        state.mode.value = ViewMode.RECOMMEND
    }

    fun selectLabel(position : Int, type : ListType){
        val selectedLabel : Label
        when(type){
            ListType.MY_LABEL -> {
                selectedLabel = myLabelList.removeAt(position)
                state.myLabel.value = myLabelList
            }
            ListType.RECENT_SEARCH_LABEL -> {
                selectedLabel = recentlySearchList.removeAt(position)
                state.recentlySearchLabel.value = recentlySearchList
            }
        }
        selectedLabelList.add(selectedLabel)
        state.selectedLabel.value = selectedLabelList
    }

    data class State(
        val mode : MutableLiveData<ViewMode> = MutableLiveData(ViewMode.RECOMMEND),
        val selectedLabel : MutableLiveData<List<Label>> = MutableLiveData(),
        val recentlySearchLabel : MutableLiveData<List<Label>> = MutableLiveData(),
        val myLabel : MutableLiveData<List<Label>> = MutableLiveData(),
        val searchValue : MutableLiveData<String> = MutableLiveData(),
        val searchResult : MutableLiveData<List<Label>> = MutableLiveData()
    )

    enum class ViewMode{
        RECOMMEND, SEARCH
    }

    enum class ListType{
        MY_LABEL, RECENT_SEARCH_LABEL
    }
}