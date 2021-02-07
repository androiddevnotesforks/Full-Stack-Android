package com.nexters.fullstack.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.mapper.LocalFileMapper
import com.nexters.fullstack.source.MainLabel
import com.nexters.fullstack.ui.adapter.MainStackAdapter

@BindingAdapter("app:setStackItems")
fun RecyclerView.setStackBinding(items: MainLabel?) {
    val stackAdapter = adapter as? MainStackAdapter

    items?.let { presenterList ->
        stackAdapter?.addItems(presenterList.images.map(LocalFileMapper::fromData))
    }

    stackAdapter?.notifyDataSetChanged()
}