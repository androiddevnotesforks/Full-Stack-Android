package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemLabelBinding
import com.nexters.fullstack.source.LabelSource

class RecommendLabelViewHolder(private val binding: ItemLabelBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item : LabelSource){
        binding.tvLabel.text = item.name
    }
}