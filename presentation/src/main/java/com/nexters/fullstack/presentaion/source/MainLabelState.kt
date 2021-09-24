package com.nexters.fullstack.presentaion.source

data class MainLabel(val images: List<PresentLocalFile>, val state: State? = null)

sealed class State {
    object Approve : State()

    object Reject : State()
}
