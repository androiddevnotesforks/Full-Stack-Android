package com.nexters.fullstack.presentaion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeList(
    val type : HomeListType,
    val title : String,
    val images : List<Screenshot>?
) : Parcelable

