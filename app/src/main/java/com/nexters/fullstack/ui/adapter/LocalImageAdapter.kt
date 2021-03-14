package com.nexters.fullstack.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemAlbumWithLabelBinding
import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.ui.holder.LocalImageViewHolder

class LocalImageAdapter : BaseAdapter<DomainUserImage>() {

    var eventAction: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LocalImageViewHolder(
            ItemAlbumWithLabelBinding.inflate(LayoutInflater.from(parent.context)),
            eventAction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LocalImageViewHolder) {
            holder.onBind(items[position])
        }
    }
}