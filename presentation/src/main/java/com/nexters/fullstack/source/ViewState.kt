package com.nexters.fullstack.source

sealed class ViewState {
    object Selected : ViewState()

    object Search : ViewState()

    object Add : ViewState()
}
