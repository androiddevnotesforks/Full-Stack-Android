package com.nexters.fullstack.base

import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.Constants
import com.nexters.fullstack.Constants.ViewType.STACK_VIEW
import com.nexters.fullstack.source.RecyclerViewSource

abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    abstract fun createView(parent: ViewGroup, viewSource: RecyclerViewSource)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            is Constants.ViewType.STACK_VIEW -> {}
        }
    }
}