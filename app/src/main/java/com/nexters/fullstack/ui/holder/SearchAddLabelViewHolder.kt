package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemSearchAddBinding
import com.nexters.fullstack.source.LabelSource

class SearchAddLabelViewHolder(private val binding : ItemSearchAddBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : LabelSource){
        binding.tvLabel.text = item.name
    }
}