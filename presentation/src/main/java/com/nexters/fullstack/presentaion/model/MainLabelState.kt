package com.nexters.fullstack.presentaion.model

data class MainLabel(val images: List<PresentLocalFile>, val state: MainLabelState? = null)

sealed class MainLabelState {
    object Approve : MainLabelState()

    object Reject : MainLabelState()
}
