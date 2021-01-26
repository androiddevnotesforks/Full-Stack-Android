package com.nexters.fullstack.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.LabelViewType
import com.nexters.fullstack.ViewTypes
import com.nexters.fullstack.source.RecyclerSource
import com.nexters.fullstack.source.RecyclerViewSource

abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun createView(
        parent: ViewGroup,
        viewSource: RecyclerViewSource
    ): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LabelViewType.STACK_VIEW -> {
                createView(parent, viewSource = RecyclerSource.CardStack)
            }
            else -> throw IllegalAccessError("unknown viewtype")
        }
    }
}