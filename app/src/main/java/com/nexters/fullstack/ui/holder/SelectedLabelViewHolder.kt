package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemSelectedLabelBinding
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.source.LabelSource

class SelectedLabelViewHolder(private val binding : ItemSelectedLabelBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : LabelSource){
        binding.tvLabel.text = item.name
    }
}