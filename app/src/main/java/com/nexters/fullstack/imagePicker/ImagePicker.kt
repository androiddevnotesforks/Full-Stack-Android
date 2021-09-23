package com.nexters.fullstack.imagePicker

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.R
import com.nexters.fullstack.presentaion.source.Screenshot
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration

class ImagePicker(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {
    private var imagePickerAdapter : ImagePickerAdapter = ImagePickerAdapter()
    private var images : ArrayList<Screenshot> = ArrayList()
    private var selectedImages : ArrayList<Screenshot> = ArrayList()
    private var colCount : Int = DEFAULT_COL_COUNT
    init{
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ImagePicker,
            0, 0).apply {
            try {
                colCount = getInteger(R.styleable.ImagePicker_count, DEFAULT_COL_COUNT)
            } finally {
                recycle()
            }
        }
        initView()
    }

    fun initView(){
        layoutManager = GridLayoutManager(context, colCount)
        addItemDecoration(SpaceBetweenRecyclerDecoration(10,10))
        imagePickerAdapter.setItemClickListener { _, i, _ ->
            selectImage(i)
        }
        adapter = imagePickerAdapter
    }

    fun setImages(images : ArrayList<Screenshot>){
        this.images = images
        imagePickerAdapter.addItems(this.images)
    }

    fun getSelectedImages() = selectedImages

    fun picker(){
        clear()
        imagePickerAdapter.changeMode()
    }

    fun selectImage(position : Int){
        images[position].isChecked = !images[position].isChecked
        if(images[position].isChecked){
            selectedImages.add(images[position])
        } else {
            selectedImages.remove(images[position])
        }
        imagePickerAdapter.addItems(images)
    }

    fun clear(){
        for (item : Screenshot in images) item.isChecked = false
        selectedImages.clear()
    }

    fun getMode() = imagePickerAdapter.getMode()

    companion object{
        const val DEFAULT_COL_COUNT = 3
    }
}