package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.SelectedLabelViewType
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemSelectedLabelBinding
import com.nexters.fullstack.source.DomainLabel
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.ui.holder.SelectedLabelViewHolder

class SelectedLabelAdapter() : BaseAdapter<LabelSource>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = SelectedLabelViewHolder(ItemSelectedLabelBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SelectedLabelViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    getItemClickListener()?.invoke(it, holder.adapterPosition, items[holder.adapterPosition])
                }
            }
        }
    }
}