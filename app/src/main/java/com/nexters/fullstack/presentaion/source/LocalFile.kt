package com.nexters.fullstack.presentaion.source

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalFile(val url: String) : Parcelable
