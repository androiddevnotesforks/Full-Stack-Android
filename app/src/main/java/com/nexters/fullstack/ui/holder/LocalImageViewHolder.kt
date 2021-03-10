package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.feature.util.ColorUtils
import com.nexters.fullstack.databinding.RecyclerAlbumItemBinding
import com.nexters.fullstack.source.local.DomainUserImage

class LocalImageViewHolder(private val binding: RecyclerAlbumItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: DomainUserImage) {
        binding.item = item
//        binding.label.setBackgroundColor(
//            ColorUtils(
//                item.labels.first().color ?: "",
//                binding.root.context
//            ).getActive()
//        )
        binding.executePendingBindings()
    }
}