package com.nexters.fullstack.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalImageData(
    val id: String,
    val originUrl: String
) : Parcelable
