package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemLabelBinding
import com.nexters.fullstack.databinding.ItemTitleCountBinding
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.ui.holder.MyLabelViewHolder
import com.nexters.fullstack.ui.holder.TitleViewHolder
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel

class OutAppLabelAdapter(state : LabelOutAppViewModel.ViewState) : BaseAdapter<LabelSource>() {
    val text = when (state){
        LabelOutAppViewModel.ViewState.MY_LABEL -> MY_LABEL_TITLE
        LabelOutAppViewModel.ViewState.RECENT_LABEL -> RECENT_SEARCH_TITLE
        LabelOutAppViewModel.ViewState.SEARCH_RESULT -> SEARCH_RESULT_TITLE
        else -> ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            2000 -> TitleViewHolder(
                ItemTitleCountBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                ),
                text
            )
            LabelSource.DEFAULT -> MyLabelViewHolder(
                ItemLabelBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    )
                )
            )
            else -> throw NotFoundViewType
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MyLabelViewHolder -> {
                holder.bind(items[position-1])
                holder.itemView.setOnClickListener {
                    getItemClickListener()?.invoke(
                        it,
                        holder.adapterPosition,
                        items[holder.adapterPosition-1]
                    )
                }
            }
            is TitleViewHolder -> {
                holder.bind()
            }
        }
    }

    override fun getItemViewType(position: Int)  =
        if(position == 0) {
            2000
        }
        else{
            items[position-1].type
        }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }


    companion object{
        const val MY_LABEL_TITLE = "내 라벨"
        const val RECENT_SEARCH_TITLE = "최근 검색한 라벨"
        const val SEARCH_RESULT_TITLE = "검색결과"
    }
}