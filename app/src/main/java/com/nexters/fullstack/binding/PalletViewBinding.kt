package com.nexters.fullstack.binding

import androidx.databinding.BindingAdapter
import com.tsdev.feature.ui.data.PalletItem
import com.tsdev.feature.ui.layout.PalletGridLayout

@BindingAdapter("app:setItems")
fun PalletGridLayout.setOnDataToLabelWithColor(data: List<PalletItem>?) {
    data?.let {
        setItems = it
    }

    setOnInitView()
}