package com.nexters.fullstack.ui.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemTitleCountBinding

class TitleViewHolder(private val binding: ItemTitleCountBinding, private val text : String): RecyclerView.ViewHolder(binding.root) {
    fun bind(){
        binding.tvTitle.text = text
    }
}