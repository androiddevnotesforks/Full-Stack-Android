package com.nexters.fullstack.imagePicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nexters.fullstack.NotFoundViewType
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ItemScreenshotBinding
import com.nexters.fullstack.source.Screenshot

class ImagePickerAdapter : BaseAdapter<Screenshot>() {
    private var mode : Mode = Mode.DEFAULT
    private lateinit var requestOptions: RequestOptions
    private lateinit var requestManager: RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePickerViewHolder {
        val viewHolder = ImagePickerViewHolder(
            ItemScreenshotBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        requestOptions = RequestOptions
            .skipMemoryCacheOf(false)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
        requestManager = Glide.with(viewHolder.itemView)
        viewHolder.itemView.setOnClickListener {
            if(mode == Mode.SELECT){
                getItemClickListener()?.invoke(
                    it,
                    viewHolder.adapterPosition,
                    items[viewHolder.adapterPosition]
                )
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        when(holder){
            is ImagePickerViewHolder -> {
                holder.bind(items[position], mode, requestManager, requestOptions)
            }
            else -> throw NotFoundViewType
        }
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
        notifyDataSetChanged()
    }

    class ImagePickerViewHolder(private val binding : ItemScreenshotBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Screenshot, mode : Mode, requestManager: RequestManager, requestOptions: RequestOptions){
            requestManager
                .load(item.imageUrl)
                .apply(requestOptions)
                .into(binding.ivScreenshot)
            with(item){
                if (isFavorite) binding.ivHeart.visibility = View.VISIBLE
                if (!labels.isNullOrEmpty()) binding.ivLabel.visibility = View.VISIBLE
                if (mode == Mode.SELECT) binding.ivSelect.visibility = View.VISIBLE
                binding.ivSelect.isSelected = item.isChecked
            }
        }
    }

}