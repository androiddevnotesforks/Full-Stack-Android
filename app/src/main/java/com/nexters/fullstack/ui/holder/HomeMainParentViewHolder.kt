package com.nexters.fullstack.ui.holder

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.databinding.ItemHomeGroupBinding
import com.nexters.fullstack.source.HomeList
import com.nexters.fullstack.ui.activity.ScreenshotDetailActivity
import com.nexters.fullstack.ui.adapter.HomeMainChildAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration

class HomeMainParentViewHolder(private val binding : ItemHomeGroupBinding) : RecyclerView.ViewHolder(binding.root) {
    val context = binding.root.context
    fun bind(item : HomeList){
        val childAdapter = HomeMainChildAdapter(item.type)
        childAdapter.addItems(item.images?:ArrayList())
        binding.tvTitle.text = item.title
        with(binding.rvImage){
            adapter = childAdapter
            addItemDecoration(SpaceBetweenRecyclerDecoration(horizontal = VERTICAL_SPACING))

        }
        childAdapter.setItemClickListener { _, _, data ->
            val intent = Intent(context, ScreenshotDetailActivity::class.java)
            intent.putExtra(IMAGE_KEY, data)
            context.startActivity(intent)
        }
    }

    companion object {
        const val VERTICAL_SPACING = 5
        const val IMAGE_KEY = "image"
    }
}