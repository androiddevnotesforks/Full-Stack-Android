package com.nexters.fullstack.domain.source

sealed class LabellingState {
    object Pending : LabellingState()

    object Approve : LabellingState()

    object Rejected : LabellingState()
}