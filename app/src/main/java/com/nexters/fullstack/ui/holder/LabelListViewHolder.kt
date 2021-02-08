package com.nexters.fullstack.ui.holder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemListLabelBinding
import com.nexters.fullstack.source.LabelSource

class LabelListViewHolder(private val binding: ItemListLabelBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LabelSource) {
        binding.label.text = item.name
        binding.hintColor.setBackgroundColor(Color.parseColor(item.color))
    }
}