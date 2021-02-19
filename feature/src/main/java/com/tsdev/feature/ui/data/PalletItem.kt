package com.tsdev.feature.ui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PalletItem(
    val name: String
) : Parcelable