package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.LabelViewType
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.source.LocalFile
import com.nexters.fullstack.source.RecyclerSource
import com.nexters.fullstack.source.RecyclerViewSource
import com.nexters.fullstack.ui.holder.MainStackItemHolder

class MainStackAdapter : BaseAdapter() {
    private val items = mutableListOf<LocalFile>()

    override fun createView(
        parent: ViewGroup,
        viewSource: RecyclerViewSource
    ): RecyclerView.ViewHolder {
        return when (viewSource) {
            is RecyclerSource.CardStack -> MainStackItemHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_stack_view, parent, false
                )
            )
            else -> throw IllegalAccessError("Unknown ViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainStackItemHolder -> {
                holder.bind(items[position])
            }
        }
    }

    private fun addItem(item: LocalFile) {
        items.add(item)
    }

    fun addItems(items: List<LocalFile>) {
        this.items.addAll(items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): LocalFile {
        return items[position]
    }

    override fun getItemViewType(position: Int): Int {
        return LabelViewType.LABEL_VIEW
    }
}