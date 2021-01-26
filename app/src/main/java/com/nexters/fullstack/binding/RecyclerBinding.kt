package com.nexters.fullstack.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.mapper.UiMapper
import com.nexters.fullstack.source.PresentLocalFile
import com.nexters.fullstack.ui.adapter.MainStackAdapter

@BindingAdapter("setStackItems")
fun RecyclerView.setStackBinding(items: List<PresentLocalFile>?) {
    val stackAdapter = adapter as? MainStackAdapter

    items?.let { presenterList ->
        stackAdapter?.addItems(presenterList.map(UiMapper::fromData))
    }

    stackAdapter?.notifyDataSetChanged()
}