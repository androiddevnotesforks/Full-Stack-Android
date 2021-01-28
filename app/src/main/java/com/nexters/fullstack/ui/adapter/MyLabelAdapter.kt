package com.nexters.fullstack.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.databinding.ItemLabelBinding
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.ui.holder.MyLabelViewHolder
import com.nexters.fullstack.ui.holder.RecommendLabelViewHolder

class MyLabelAdapter(private val context: Context, private val labels : ArrayList<Label>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            Label.DEFAULT -> MyLabelViewHolder(
                ItemLabelBinding.inflate(
                    LayoutInflater.from(
                        context
                    )
                ))
            Label.RECOMMEND -> RecommendLabelViewHolder(
                ItemLabelBinding.inflate(
                    LayoutInflater.from(
                        context
                    )
                ))
            else -> throw NotFoundViewType
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MyLabelViewHolder -> holder.bind(labels[position])
            is RecommendLabelViewHolder -> holder.bind(labels[position])
        }
    }

    override fun getItemCount() = labels.size

    override fun getItemViewType(position: Int) = labels[position].type

}