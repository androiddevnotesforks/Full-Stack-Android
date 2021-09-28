package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.feature.BR
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.LabelAlbumItemBinding
import com.nexters.fullstack.domain.entity.LocalImageDomain

class LabelAlbumRecyclerAdapter(private val onClickItem: (LocalImageDomain) -> Unit) :
    BaseAdapter<LocalImageDomain>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LabelAlbumViewHolder(
            LabelAlbumItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LabelAlbumViewHolder) {
            holder.onBind(items[position])
        }
    }

    inner class LabelAlbumViewHolder(private val binding: LabelAlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.album.setOnClickListener { onClickItem.invoke(items[adapterPosition]) }
        }

        fun onBind(item: LocalImageDomain) {
            binding.run {
                setVariable(BR.data, item)
                executePendingBindings()
            }
        }
    }

}