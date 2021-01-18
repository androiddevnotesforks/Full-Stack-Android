package com.nexters.fullstack.source

sealed class BottomSheet {
    object Label : BottomSheet()

    object Search : BottomSheet()

    object Album : BottomSheet()
}