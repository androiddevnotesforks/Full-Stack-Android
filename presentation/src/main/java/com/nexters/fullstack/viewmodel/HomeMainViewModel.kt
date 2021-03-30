package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.HomeList
import com.nexters.fullstack.source.HomeScreenshot
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    private val state: State = State()
    private val screenshotGroupList = mutableListOf<HomeList>()
    private val recentScreenshotList = mutableListOf<HomeScreenshot>()
    private val favoriteScreenshotList = mutableListOf<HomeScreenshot>()

    fun state(): State = state

    init {
        // TODO load screenshots..!
        viewModelScope.launch {
            recentScreenshotList.add(HomeScreenshot(null, null, false))
            favoriteScreenshotList.add(HomeScreenshot(null, null, true))
            screenshotGroupList.add(HomeList("최신순 스크린샷", recentScreenshotList))
            screenshotGroupList.add(HomeList("즐겨찾는 스크린샷", favoriteScreenshotList))
        }
        state.screenshotGroups.value = screenshotGroupList
    }

    data class State(
        val screenshotGroups : MutableLiveData<List<HomeList>> = MutableLiveData()
    )

}