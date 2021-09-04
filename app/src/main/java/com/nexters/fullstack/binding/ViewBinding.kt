package com.nexters.fullstack.binding

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nexters.fullstack.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
