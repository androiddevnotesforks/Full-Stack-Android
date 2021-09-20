package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.presentaion.source.Screenshot
import com.nexters.fullstack.ui.holder.HomeMainChildViewHolder
import com.nexters.fullstack.viewmodel.HomeScreenshotViewModel

class HomeScreenshotAdapter(private val mode : HomeScreenshotViewModel.Mode) : BaseAdapter<Screenshot>() {
    private val selectedIndex : ArrayList<Int> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeMainChildViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_screenshot,
                parent,
                false
            ),
            mode
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HomeMainChildViewHolder -> {
                holder.bind(items[position])
                holder.itemView.setOnClickListener {
                    getItemClickListener()?.invoke(
                        it,
                        position,
                        items[position]
                    )
                    if(selectedIndex.contains(position)){
                        items[position].isChecked = false
                        notifyDataSetChanged()
                        selectedIndex.remove(position)
                    }
                    else{
                        items[position].isChecked = true
                        notifyDataSetChanged()
                        selectedIndex.add(position)
                    }
                }
            }
        }
    }

    fun getSelectedList() = selectedIndex


}