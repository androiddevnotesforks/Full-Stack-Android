package com.nexters.fullstack.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.mapper.LocalFileMapper
import com.nexters.fullstack.mapper.LocalMainLabelMapper
import com.nexters.fullstack.source.LocalLabel
import com.nexters.fullstack.source.MainLabel
import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.ui.adapter.LocalImageAdapter
import com.nexters.fullstack.ui.adapter.MainStackAdapter
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration

@BindingAdapter("app:setStackItems")
fun RecyclerView.setStackBinding(items: MainLabel?) {
    val stackAdapter = adapter as? MainStackAdapter

    items?.let { presenterList ->
        stackAdapter?.addItems(presenterList.images.map(LocalFileMapper::fromData))
    }

    stackAdapter?.notifyDataSetChanged()
}

@BindingAdapter("app:localLabels")
fun RecyclerView.setLocalLabel(items: LocalLabel?) {
    val localAdapter = adapter as? MyLabelAdapter

    items?.let { localLabel ->
        localAdapter?.addItems(localLabel.items.map(LocalMainLabelMapper::toData))
    }

    localAdapter?.notifyDataSetChanged()
}

@BindingAdapter("app:localImages", "app:eventAction")
fun RecyclerView.setLocalImage(
    items: List<DomainUserImage>?,
    event: Any?
) {
    adapter?.run {
        if (this is LocalImageAdapter) {
            eventAction = event
            items?.let {
                addItems(it)
                notifyDataSetChanged()
            }
        }
    } ?: run {
        LocalImageAdapter().also {
            adapter = it
            addItemDecoration(SpaceBetweenRecyclerDecoration(14, 10))
        }
    }
}
