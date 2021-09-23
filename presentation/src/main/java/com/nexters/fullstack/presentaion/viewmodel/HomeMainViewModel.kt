package com.nexters.fullstack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nexters.fullstack.BaseViewModel
import com.nexters.fullstack.presentaion.source.HomeList
import com.nexters.fullstack.presentaion.source.HomeListType
import com.nexters.fullstack.presentaion.source.Screenshot
import kotlinx.coroutines.launch

class HomeMainViewModel : BaseViewModel() {
    private val state: State = State()
    private val screenshotGroupList = mutableListOf<HomeList>()
    private val recentScreenshotList = mutableListOf<Screenshot>()
    private val favoriteScreenshotList = mutableListOf<Screenshot>()

    fun state(): State = state

    init {
        // TODO load screenshots..!
        viewModelScope.launch {
            recentScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/arctichare.png", null, false))
            recentScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, false))
            recentScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, false))
            recentScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, false))
            favoriteScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, true))
            favoriteScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, true))
            favoriteScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, true))
            favoriteScreenshotList.add(Screenshot("https://homepages.cae.wisc.edu/~ece533/images/airplane.png", null, true))
            screenshotGroupList.add(HomeList(HomeListType.RECENT, HomeListType.RECENT.title, recentScreenshotList))
            screenshotGroupList.add(HomeList(HomeListType.FAVORITE, HomeListType.FAVORITE.title,favoriteScreenshotList))
        }
        state.screenshotGroups.value = screenshotGroupList
    }

    data class State(
        val screenshotGroups : MutableLiveData<List<HomeList>> = MutableLiveData()
    )

}