package com.nexters.fullstack.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.databinding.ItemScreenshotBinding
import com.nexters.fullstack.presentaion.source.Screenshot
import com.nexters.fullstack.viewmodel.HomeScreenshotViewModel

class HomeMainChildViewHolder(private val binding : ItemScreenshotBinding, private val mode : HomeScreenshotViewModel.Mode) : RecyclerView.ViewHolder(binding.root){
    fun bind(item : Screenshot){
        Glide.with(itemView.context).load(item.imageUrl).into(binding.ivScreenshot)
        with(item){
            if (isFavorite) binding.ivHeart.visibility = View.VISIBLE
            if (!labels.isNullOrEmpty()) binding.ivLabel.visibility = View.VISIBLE
            if (mode == HomeScreenshotViewModel.Mode.SELECTION) binding.ivSelect.visibility = View.VISIBLE
            binding.ivSelect.isSelected = item.isChecked
        }
    }
}