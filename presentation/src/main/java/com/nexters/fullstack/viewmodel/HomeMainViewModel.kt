package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.source.HomeList
import com.nexters.fullstack.source.HomeListType
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
//            recentScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, false))
//            recentScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, false))
//            recentScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, false))
//            recentScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, false))
//            favoriteScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, true))
//            favoriteScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, true))
//            favoriteScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, true))
//            favoriteScreenshotList.add(HomeScreenshot("https://t1.daumcdn.net/cfile/tistory/0138F14A517F77713A", null, true))
            screenshotGroupList.add(HomeList(HomeListType.RECENT, HomeListType.RECENT.title, recentScreenshotList))
            screenshotGroupList.add(HomeList(HomeListType.FAVORITE, HomeListType.FAVORITE.title,favoriteScreenshotList))
        }
        state.screenshotGroups.value = screenshotGroupList
    }

    data class State(
        val screenshotGroups : MutableLiveData<List<HomeList>> = MutableLiveData()
    )

}