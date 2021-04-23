package com.nexters.fullstack.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.databinding.ItemHomeScreenshotBinding
import com.nexters.fullstack.source.HomeScreenshot

class HomeMainChildViewHolder(private val binding : ItemHomeScreenshotBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item : HomeScreenshot){
        Glide.with(itemView.context).load(item.imageUrl).into(binding.ivScreenshot)
        with(item){
            if (isFavorite) binding.ivHeart.visibility = View.VISIBLE
            if (!labels.isNullOrEmpty()) binding.ivLabel.visibility = View.VISIBLE
        }
    }
}