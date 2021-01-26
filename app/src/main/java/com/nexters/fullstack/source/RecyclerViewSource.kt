package com.nexters.fullstack.source

interface RecyclerViewSource

sealed class RecyclerSource {
    object CardStack : RecyclerSource(), RecyclerViewSource
}