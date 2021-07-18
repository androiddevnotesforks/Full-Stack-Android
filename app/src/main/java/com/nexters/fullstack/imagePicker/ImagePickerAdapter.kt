package com.nexters.fullstack.imagePicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemScreenshotBinding
import com.nexters.fullstack.source.Screenshot

class ImagePickerAdapter : BaseAdapter<Screenshot>() {
    private var mode : Mode = Mode.DEFAULT

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is ImagePickerViewHolder -> {
                holder.bind(items[position], mode)
                holder.itemView.setOnClickListener {
                    if(mode == Mode.SELECT){
                        getItemClickListener()?.invoke(
                            it,
                            position,
                            items[position]
                        )
                    }
                }
            }
            else -> throw NotFoundViewType
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePickerViewHolder {
        return ImagePickerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_screenshot,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getMode() = mode

    fun changeMode(){
        mode = when(mode){
            Mode.DEFAULT -> Mode.SELECT
            Mode.SELECT -> Mode.DEFAULT
        }

    }

    class ImagePickerViewHolder(private val binding : ItemScreenshotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Screenshot, mode : Mode){
            Glide.with(itemView.context).load(item.imageUrl).into(binding.ivScreenshot)
            with(item){
                if (isFavorite) binding.ivHeart.visibility = View.VISIBLE
                if (!labels.isNullOrEmpty()) binding.ivLabel.visibility = View.VISIBLE
                if (mode == Mode.SELECT) binding.ivSelect.visibility = View.VISIBLE
                binding.ivSelect.isSelected = item.isChecked
            }
        }
    }

}