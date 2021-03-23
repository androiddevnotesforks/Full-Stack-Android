package com.nexters.fullstack.ui.widget.bottomsheet.mapper

import com.nexters.feature.ui.data.pallet.PalletItem
import com.nexters.fullstack.db.entity.UserLabel

fun UserLabel.mapToPallet(): PalletItem {
    return PalletItem(name = color)
}