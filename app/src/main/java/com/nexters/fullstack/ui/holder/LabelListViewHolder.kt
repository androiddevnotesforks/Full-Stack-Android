package com.nexters.fullstack.ui.holder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemListLabelBinding
import com.nexters.fullstack.presentaion.source.LabelSource
import com.nexters.feature.util.ColorUtils

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
        binding.hintColor.setBackgroundColor(
            ColorUtils(
                item.color,
                binding.root.context
            ).getActive()
        )
        if (selectedList.contains(item)) {
            binding.labelView.setBackgroundColor(
                ColorUtils(
                    item.color,
                    binding.root.context
                ).getActive()
            )
        } else {
            binding.labelView.setBackgroundColor(Color.parseColor(DEFAULT_COLOR))
        }
    }

    companion object {
        private const val DEFAULT_COLOR = "#292C33"
    }
}