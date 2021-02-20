package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemLocalSearchViewBinding
import com.nexters.fullstack.source.LabelSource
import com.tsdev.feature.util.ColorUtils

class SearchLocalListViewHolder(
    onSearchLabelClickListener: (Int) -> Unit,
    private val binding: ItemLocalSearchViewBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.localCardView.setOnClickListener {
            onSearchLabelClickListener(adapterPosition)
        }
    }

    fun bind(item: LabelSource) {
        binding.item.text = item.name
        binding.item.setTextColor(ColorUtils(item.color, binding.root.context).getText())
        binding.localCardView.setBackgroundColor(
            ColorUtils(
                item.color,
                binding.root.context
            ).getInactive()
        )
    }
}