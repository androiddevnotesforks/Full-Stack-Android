package com.nexters.fullstack.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class LocalImageData(
    val id: Long = UUID.randomUUID().mostSignificantBits,
    val originUrl: String
) : Parcelable
