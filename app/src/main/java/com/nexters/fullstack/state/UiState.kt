package com.nexters.fullstack.state

//recycler state
sealed class UiState {
    object Loaded : UiState()

    object Loading : UiState()

    object Update : UiState()
}