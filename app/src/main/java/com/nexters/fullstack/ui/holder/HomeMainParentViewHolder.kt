package com.nexters.fullstack.ui.holder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemHomeGroupBinding
import com.nexters.fullstack.source.HomeList
import com.nexters.fullstack.ui.adapter.HomeMainChildAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration

class HomeMainParentViewHolder(private val binding : ItemHomeGroupBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : HomeList){
        val childAdapter = HomeMainChildAdapter(item.type)
        childAdapter.addItems(item.images?:ArrayList())
        binding.tvTitle.text = item.title
        with(binding.rvImage){
            adapter = childAdapter
            addItemDecoration(SpaceBetweenRecyclerDecoration(horizontal = VERTICAL_SPACING))
        }
    }

    companion object {
        const val VERTICAL_SPACING = 5
    }
}