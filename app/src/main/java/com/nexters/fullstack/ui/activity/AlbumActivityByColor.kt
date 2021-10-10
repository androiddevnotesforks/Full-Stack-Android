package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import com.nexters.fullstack.util.Constants
import com.nexters.fullstack.util.Constants.DETAIL_IMAGE
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.LabelAlbumDelegate
import com.nexters.fullstack.databinding.ActivityAlbumActivitybyColorBinding
import com.nexters.fullstack.data.mapper.FileImageMapper
import com.nexters.fullstack.presentaion.model.LabelViewData
import com.nexters.fullstack.data.model.FileImage
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.ui.activity.detail.DetailAlbumActivity
import com.nexters.fullstack.util.ColorUtil
import com.nexters.fullstack.presentaion.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

class AlbumActivityByColor : BaseActivity<ActivityAlbumActivitybyColorBinding, AlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_album_activityby_color
    override val viewModel: AlbumViewModel by viewModel()

    lateinit var labelName: LabelViewData
    lateinit var labelColor: String
    lateinit var images: List<FileImage>


    private val albumItemClickListener = object : LabelAlbumDelegate {
        override fun onClick(item: FileImageEntity) {
            val intent = Intent(this@AlbumActivityByColor, DetailAlbumActivity::class.java)
            intent.putExtra(DETAIL_IMAGE, item)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            it.onClickEvent = albumItemClickListener
            it.vm = viewModel
            it.executePendingBindings()
        }

        setInitData()
        setObserve()
    }

    private fun setInitData() {
        val labelSource = intent.getParcelableExtra(Constants.LABEL) as? LabelViewData ?: throw IllegalStateException("required data of label")
        val intentImages = intent.getParcelableArrayListExtra<FileImage>(Constants.KEY_IMAGES)
        labelName = labelSource
        labelColor = labelSource.color
        images = (intentImages?.toList() ?: emptyList())

        val localImage = images.map(FileImageMapper::fromData)
        with(viewModel.input) {
            setLabelName(labelName.name)
            setImages(localImage)
        }

        binding.tvSelectImage.setTextColor(ColorUtil(viewModel.output.getLabelColor().value ?: labelColor).getActive())
    }

    private fun setObserve() {
        with(viewModel.output) {
            finishActivity().observe(this@AlbumActivityByColor) {
                finish()
            }
        }
    }
}
