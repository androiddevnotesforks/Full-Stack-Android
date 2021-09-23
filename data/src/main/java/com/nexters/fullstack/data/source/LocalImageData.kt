package com.nexters.fullstack.data.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class LocalImageData(
    val id: String,
    val originUrl: String
) : Parcelable
