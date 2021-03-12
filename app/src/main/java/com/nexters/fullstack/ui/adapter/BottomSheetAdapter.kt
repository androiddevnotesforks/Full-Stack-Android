package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemBottomSheetLabelDeleteBinding
import com.nexters.fullstack.databinding.ItemBottomSheetLabelUpdateBinding
import com.nexters.fullstack.source.bottomsheet.BottomSheetItem
import com.nexters.fullstack.source.local.DomainUserImage

class BottomSheetAdapter : BaseAdapter<BottomSheetItem>() {
    lateinit var onClickListener: (DomainUserImage) -> Unit
    lateinit var userImage: DomainUserImage

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            7000 -> {
                LabelUpdateViewHolder(
                    ItemBottomSheetLabelUpdateBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        )
                    ),
                    onClickListener
                )
            }
            7001 -> {
                LabelDeleteViewHolder(
                    ItemBottomSheetLabelDeleteBinding.inflate(
                        LayoutInflater.from(parent.context)
                    )
                )
            }
            else -> throw NotFoundViewType
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LabelDeleteViewHolder -> {
                //todo bind ViewHolder item, onClickListener..
                holder.bind(items[position])
            }
            is LabelUpdateViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    private inner class LabelUpdateViewHolder(
        private val binding: ItemBottomSheetLabelUpdateBinding,
        private val onClickListener: (DomainUserImage) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemContainer.setOnClickListener {
                onClickListener(userImage)
            }
        }

        fun bind(bindItem: BottomSheetItem) {
            binding.item = bindItem
            binding.executePendingBindings()
        }

    }

    private inner class LabelDeleteViewHolder(private val binding: ItemBottomSheetLabelDeleteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bindItem: BottomSheetItem) {
            binding.item = bindItem
            binding.executePendingBindings()
        }
    }

}