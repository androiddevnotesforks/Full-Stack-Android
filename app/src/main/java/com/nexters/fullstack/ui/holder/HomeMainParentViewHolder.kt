package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemHomeGroupBinding
import com.nexters.fullstack.source.HomeList

class HomeMainParentViewHolder(private val binding : ItemHomeGroupBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : HomeList){
        binding.tvTitle.text = item.title
        // TODO adapter binding
    }
}