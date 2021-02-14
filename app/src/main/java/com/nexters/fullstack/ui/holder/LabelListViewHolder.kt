package com.nexters.fullstack.ui.holder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemListLabelBinding
import com.nexters.fullstack.source.LabelSource

class LabelListViewHolder(
    private val onLabelClickListener: (position: Int) -> Unit,
    private val binding: ItemListLabelBinding
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        binding.labelView.setOnClickListener {
            onLabelClickListener(adapterPosition)
        }
    }

    fun bind(selectedList: List<LabelSource>, item: LabelSource) {
        binding.label.text = item.name
        binding.labelView.setBackgroundColor(Color.parseColor(item.color))
        if (selectedList.contains(item)) {
            binding.labelView.setBackgroundColor(Color.parseColor(item.color))
        } else {
            binding.hintColor.setBackgroundColor(Color.parseColor(item.color))
            binding.labelView.setBackgroundColor(Color.parseColor(DEFAULT_COLOR))
        }
    }

    companion object {
        private const val DEFAULT_COLOR = "#292C33"
    }
}