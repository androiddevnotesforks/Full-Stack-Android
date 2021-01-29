package com.nexters.fullstack.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.SelectedLabelViewType
import com.nexters.fullstack.databinding.ItemSelectedLabelBinding
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.ui.holder.SelectedLabelViewHolder

class SelectedLabelAdapter(private val context: Context, private val labels : List<Label>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            SelectedLabelViewType.SELECTED_LABEL_VIEW -> SelectedLabelViewHolder(ItemSelectedLabelBinding.inflate(LayoutInflater.from(context)))
            else -> throw NotFoundViewType
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SelectedLabelViewHolder -> holder.bind(labels[position])
        }
    }

    override fun getItemCount(): Int = labels.size
}