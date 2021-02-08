package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemLabelBinding
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.Label

class RecommendLabelViewHolder(private val binding: ItemLabelBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Label){
        binding.tvLabel.text = item.name
    }
}