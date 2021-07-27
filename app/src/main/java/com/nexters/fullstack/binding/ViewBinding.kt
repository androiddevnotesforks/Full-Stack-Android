package com.nexters.fullstack.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nexters.fullstack.R

object ViewBinding {
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
}