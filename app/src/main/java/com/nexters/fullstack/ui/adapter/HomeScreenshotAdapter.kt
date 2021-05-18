package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.source.HomeScreenshot
import com.nexters.fullstack.ui.holder.HomeMainChildViewHolder

class HomeScreenshotAdapter : BaseAdapter<HomeScreenshot>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeMainChildViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home_screenshot,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeMainChildViewHolder -> {
                holder.bind(items[position])
            }
        }
    }
}