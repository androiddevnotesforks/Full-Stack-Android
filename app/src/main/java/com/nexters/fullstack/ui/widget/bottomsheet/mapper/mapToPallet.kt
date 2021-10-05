package com.nexters.fullstack.ui.widget.bottomsheet.mapper

import com.nexters.feature.ui.data.pallet.PalletItem
import com.nexters.fullstack.data.db.entity.LabelModel

fun LabelModel.mapToPallet(): PalletItem {
    return PalletItem(name = color)
}