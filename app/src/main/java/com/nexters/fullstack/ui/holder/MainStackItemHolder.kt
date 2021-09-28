package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.databinding.ItemStackViewBinding
import com.nexters.fullstack.presentaion.model.PresentLocalFile

class MainStackItemHolder(private val binding: ItemStackViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PresentLocalFile) {
        Glide.with(binding.screenShot)
            .load(item.url)
            .into(binding.screenShot)
    }
}