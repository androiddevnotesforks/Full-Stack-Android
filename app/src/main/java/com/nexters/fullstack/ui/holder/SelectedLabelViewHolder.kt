package com.nexters.fullstack.ui.holder

import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemSelectedLabelBinding
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.util.ColorUtil

class SelectedLabelViewHolder(private val binding : ItemSelectedLabelBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Label){
        val colorUtil = ColorUtil(item.color)
        binding.tvLabel.text = item.name
        DrawableCompat.setTint(binding.container.background, colorUtil.getInactive())
        binding.tvLabel.setTextColor(colorUtil.getActive())
        binding.ivDelete.setColorFilter(colorUtil.getActive())
    }
}