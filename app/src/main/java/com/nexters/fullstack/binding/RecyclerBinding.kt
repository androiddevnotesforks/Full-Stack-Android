package com.nexters.fullstack.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.ext.toPx
import com.nexters.fullstack.mapper.LocalFileMapper
import com.nexters.fullstack.mapper.LocalMainLabelMapper
import com.nexters.fullstack.source.LabelingImage
import com.nexters.fullstack.source.LocalImageData
import com.nexters.fullstack.source.LocalLabel
import com.nexters.fullstack.source.MainLabel
import com.nexters.fullstack.source.bottomsheet.BottomSheetItem
import com.nexters.fullstack.source.data.LocalImageDomain
import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.source.local.DomainUserLabel
import com.nexters.fullstack.ui.adapter.*
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.nexters.fullstack.ui.holder.LocalImageViewHolder

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

@BindingAdapter("app:localImages", "app:eventAction", "app:onClickDelegate")
fun RecyclerView.setLocalImage(
    items: List<Map<DomainUserLabel, List<LocalImageDomain>>>?,
    event: Any?,
    onClickItemDelegate: Any?
) {
    val item = mutableListOf<LabelingImage>()
    adapter?.run {
        if (this is LocalImageAdapter) {
            eventAction = event
            delegate = onClickItemDelegate
            items?.let {
                it.forEach { domainItemMap ->
                    domainItemMap.mapKeys { mapItem ->
                        if (!item.contains(LabelingImage(mapItem.key, mapItem.value))) {
                            item.add(LabelingImage(mapItem.key, mapItem.value))
                        }
                    }
                }
                addItems(item)
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

@BindingAdapter("app:bottomSheetItem", "app:onClickEvent")
fun RecyclerView.setBottomSheetItem(items: List<BottomSheetItem>?, onClickEvent: Any?) {
    adapter?.run {
        if (this is BottomSheetAdapter) {
            items?.let {
                addItems(it)
                notifyDataSetChanged()
            }
        }
    } ?: BottomSheetAdapter(onClickEvent).also {
        adapter = it
    }
}

@BindingAdapter(requireAll = false, value = ["app:labelAlbumItems", "app:onClickLabelItemEvent"])
fun RecyclerView.setLabelAlbumItems(items: List<LocalImageDomain>?, event: LabelAlbumDelegate?) {
    adapter?.let { labelAdapter ->
        Log.e("adapter", "call")
        if (labelAdapter is LabelAlbumRecyclerAdapter) {
            items?.let {
                labelAdapter.addItems(it)
                labelAdapter.notifyDataSetChanged()
            }
        }
    } ?: run {
        Log.e("run", "call")
        event?.let {
            Log.e("event", "call")
            val labelAlbumAdapter = LabelAlbumRecyclerAdapter(it::onClick)

            addItemDecoration(SpaceBetweenRecyclerDecoration(vertical = 16, horizontal = 14))
            adapter = labelAlbumAdapter
        }
    }
}

interface LabelAlbumDelegate {
    fun onClick(item: LocalImageDomain)
}
