package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemListLabelBinding
import com.nexters.fullstack.databinding.ItemSelectedLabelBinding
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.ui.holder.LabelListViewHolder
import com.nexters.fullstack.ui.holder.LabelingSelectedViewHolder

class LabelingSelectAdapter : BaseAdapter<LabelSource>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LabelSource.LIST -> {
                LabelListViewHolder(ItemListLabelBinding.inflate(LayoutInflater.from(parent.context)))
            }
            LabelSource.SELECTED -> {
                LabelingSelectedViewHolder(
                    ItemSelectedLabelBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        )
                    )
                )
            }
            else -> throw NotFoundViewType
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LabelListViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }
}