package com.nexters.fullstack.databinding

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nexters.fullstack.R
import com.nexters.fullstack.domain.entity.DomainUserLabel
import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.presentaion.model.LabelSource
import com.nexters.fullstack.presentaion.model.LabelingImage
import com.nexters.fullstack.presentaion.model.MainLabel
import com.nexters.fullstack.presentaion.model.MainLabelState
import com.nexters.fullstack.presentaion.model.bottomsheet.BottomSheetItem
import com.nexters.fullstack.presentaion.viewmodel.MainViewModel
import com.nexters.fullstack.ui.adapter.*
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.yuyakaido.android.cardstackview.CardStackView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:localImage")
    fun ImageView.setLocalImageThumbnail(url: String?) {
        url?.let {
            Glide.with(this)
                .load(it)
                .apply(RequestOptions().centerCrop())
                .into(this)
        } ?: run {
            Glide.with(this)
                .load(R.drawable.ic_ico_empty_screenshot)
                .into(this)
        }
    }


    @JvmStatic
    @BindingAdapter("app:labelAlbumImage")
    fun ImageView.setLabelAlbumImage(url: String?) {
        url?.let {
            Glide.with(this)
                .load(it)
                .apply(RequestOptions().centerCrop())
                .into(this)
        } ?: run {
            Glide.with(this)
                .load(R.drawable.ic_ico_empty_screenshot)
                .into(this)
        }
    }

    @JvmStatic
    @BindingConversion
    fun convertBooleanToVisibility(value: Boolean): Int {
        return if (value) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter("app:textUtil")
    fun TextView.setTextDateFormat(value: String?) {
        val splitText = value?.split("_")
        val dateTime = splitText?.get(splitText.lastIndex)?.split("-")


        val year = dateTime?.firstOrNull()?.substring(0..3)
        val month = dateTime?.firstOrNull()?.substring(4..5)
        val day = dateTime?.firstOrNull()?.substring(6, dateTime.firstOrNull()?.length ?: return)


        text = context.getString(R.string.detail_album_activity_title, year, month, day)
    }
}

@BindingAdapter("app:setStackItems")
fun RecyclerView.setStackBinding(items: MainLabel?) {
    val stackAdapter = adapter as? MainStackAdapter

    items?.let { presenterList ->
        stackAdapter?.addItems(presenterList.images)
    }

    stackAdapter?.notifyDataSetChanged()
}

@BindingAdapter("app:localLabels")
fun RecyclerView.setLocalLabel(items: List<LabelSource>?) {
    val localAdapter = adapter as? MyLabelAdapter

    items?.let {
        localAdapter?.addItems(it)
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

@BindingAdapter(
    "app:approve",
    "app:targetView",
    "app:onApproveButtonClickListener",
    requireAll = true
)
fun ImageView.setOnApproveButtonClickListener(
    isSwipe: Boolean?,
    cardStackView: CardStackView?,
    emit: MainViewModel.MainInput?
) {
    var data = isSwipe
    setOnClickListener {
        data = false
        if (data != false) {
            return@setOnClickListener
        }
        cardStackView?.swipe()
        emit?.onClickedButton(MainLabelState.Approve)
    }
}

@BindingAdapter("app:onRejectButtonClickListener")
fun ImageView.setOnRejectButtonClickListener(emit: MainViewModel.MainInput?) {
    setOnClickListener {
        emit?.onClickedButton(MainLabelState.Reject)
    }
}


interface LabelAlbumDelegate {
    fun onClick(item: LocalImageDomain)
}
