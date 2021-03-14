package com.nexters.fullstack.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.nexters.feature.BR
import com.nexters.fullstack.databinding.ItemAlbumWithLabelBinding
import com.nexters.fullstack.source.local.DomainUserImage

class LocalImageViewHolder(
    private val binding: ItemAlbumWithLabelBinding,
    private val onClick: Any?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: DomainUserImage) {
        binding.item = item
        binding.setVariable(BR.clickEvent, onClick)
//        binding.label.setBackgroundColor(
//            ColorUtils(
//                item.labels.first().color ?: "",
//                binding.root.context
//            ).getActive()
//        )
        binding.executePendingBindings()
    }
}