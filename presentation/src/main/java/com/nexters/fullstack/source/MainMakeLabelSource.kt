package com.nexters.fullstack.source

import android.os.Parcelable
import com.nexters.feature.ui.data.PalletItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainMakeLabelSource(val labelText: String, val palletItem: PalletItem? = null) :
    Parcelable