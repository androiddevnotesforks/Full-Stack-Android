package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nexters.fullstack.base.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.R
import com.nexters.fullstack.presentaion.model.Screenshot
import com.nexters.fullstack.ui.holder.HomeMainChildViewHolder
import com.nexters.fullstack.ui.holder.ScreenshotSearchNoResultViewHolder
import com.nexters.fullstack.presentaion.viewmodel.HomeScreenshotViewModel

class HomeScreenshotResultAdapter: BaseAdapter<Screenshot>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0 -> return ScreenshotSearchNoResultViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_screenshot_no_result,
                    parent,
                    false
                )
            )
            else -> return HomeMainChildViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_screenshot,
                    parent,
                    false
                ),
                HomeScreenshotViewModel.Mode.DEFAULT
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeMainChildViewHolder -> {
                holder.bind(items[position])
            }
            is ScreenshotSearchNoResultViewHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(items.isNullOrEmpty()) 0
        else 1
    }
}