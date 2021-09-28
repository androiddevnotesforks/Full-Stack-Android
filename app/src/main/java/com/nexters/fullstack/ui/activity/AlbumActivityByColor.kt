package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import com.nexters.fullstack.util.Constants
import com.nexters.fullstack.util.Constants.DETAIL_IMAGE
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.LabelAlbumDelegate
import com.nexters.fullstack.databinding.ActivityAlbumActivitybyColorBinding
import com.nexters.fullstack.data.mapper.LocalImageMapper
import com.nexters.fullstack.presentaion.model.LabelSource
import com.nexters.fullstack.data.model.LocalImageData
import com.nexters.fullstack.domain.entity.LocalImageDomain
import com.nexters.fullstack.ui.activity.detail.DetailAlbumActivity
import com.nexters.fullstack.util.ColorUtil
import com.nexters.fullstack.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumActivityByColor : BaseActivity<ActivityAlbumActivitybyColorBinding, AlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_album_activityby_color
    override val viewModel: AlbumViewModel by viewModel()

    lateinit var labelName: LabelSource
    lateinit var labelColor: String
    lateinit var images: List<LocalImageData>


    private val albumItemClickListener = object : LabelAlbumDelegate {
        override fun onClick(item: LocalImageDomain) {
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
        val labelSource = intent.getParcelableExtra(Constants.LABEL) ?: LabelSource(
            name = "N/A",
            color = "Yellow"
        )
        val intentImages = intent.getParcelableArrayListExtra<LocalImageData>(Constants.KEY_IMAGES)
        labelName = labelSource
        labelColor = labelSource.color
        images = (intentImages?.toList() ?: emptyList())

        val localImage = images.map(LocalImageMapper::fromData)
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
