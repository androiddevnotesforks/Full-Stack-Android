package com.nexters.fullstack.domain.entity

sealed class LabellingState {
    object Pending : LabellingState()

    object Approve : LabellingState()

    object Rejected : LabellingState()
}