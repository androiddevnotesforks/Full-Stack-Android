package com.nexters.fullstack.source

data class MainLabel(val images: List<PresentLocalFile>, val state: State)

sealed class State {
    object Approve : State()

    object Reject : State()
}
