package com.nexters.fullstack.presentaion.source

sealed class ViewState {
    object Selected : ViewState()

    object Search : ViewState()

    object Add : ViewState()
}
