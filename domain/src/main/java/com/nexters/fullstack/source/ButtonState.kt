package com.nexters.fullstack.source

sealed class LabellingState {
    object Pending : LabellingState()

    object Approve : LabellingState()

    object Rejected : LabellingState()
}