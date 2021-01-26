package com.nexters.fullstack.ui.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.databinding.ItemStackViewBinding
import com.nexters.fullstack.source.LocalFile

class MainStackItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = DataBindingUtil.bind<ItemStackViewBinding>(view)!!

    fun bind(item: LocalFile) {
        Glide.with(binding.screenShot)
            .load(item.url)
            .into(binding.screenShot)
    }
}