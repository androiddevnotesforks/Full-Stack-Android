package com.nexters.fullstack.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalFile(val url: String) : Parcelable
