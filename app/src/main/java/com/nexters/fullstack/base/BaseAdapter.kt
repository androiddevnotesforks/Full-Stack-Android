package com.nexters.fullstack.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.util.DiffUtilCallback

abstract class BaseAdapter<ITEM : Any> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected val items = mutableListOf<ITEM>()

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: ITEM) {
        items.add(item)
    }

    fun addItems(items: List<ITEM>) {
        this.items.addAll(items)
    }

    fun getItem(position: Int): ITEM {
        return items[position]
    }

    fun calDiff(newItems : MutableList<ITEM>){
        val diffUtilCallback = DiffUtilCallback(items, newItems)
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilCallback)
        diffResult.dispatchUpdatesTo(this)
    }
}