package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import com.nexters.fullstack.util.Constants
import com.nexters.fullstack.util.Constants.DETAIL_IMAGE
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.data.mapper.ImageModelMapper
import com.nexters.fullstack.databinding.LabelAlbumDelegate
import com.nexters.fullstack.databinding.ActivityAlbumActivitybyColorBinding
import com.nexters.fullstack.presentaion.model.LabelViewData
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.presentaion.mapper.LocalMainLabelMapper
import com.nexters.fullstack.ui.activity.detail.DetailAlbumActivity
import com.nexters.fullstack.presentaion.viewmodel.AlbumViewModel
import com.nexters.fullstack.util.ColorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

class AlbumActivityByColor : BaseActivity<ActivityAlbumActivitybyColorBinding, AlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_album_activityby_color
    override val viewModel: AlbumViewModel by viewModel()

    private val albumItemClickListener = object : LabelAlbumDelegate {
        override fun onClick(item: ImageEntity) {
            val intent = Intent(this@AlbumActivityByColor, DetailAlbumActivity::class.java)
            intent.putExtra(DETAIL_IMAGE, ImageModelMapper.fromData(item).image)
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
        val labelSource = intent.getParcelableExtra(Constants.LABEL) as? LabelViewData
            ?: throw IllegalStateException("required data of label")

        viewModel.input.setLabelName(labelSource.name)
        viewModel.input.setLabelColor(labelSource.color)
        viewModel.fetchImages(LocalMainLabelMapper.toData(labelSource))
    }

    private fun setObserve() {
        with(viewModel.output) {
            finishActivity().observe(this@AlbumActivityByColor) {
                finish()
            }
            getLabelColor().observe(this@AlbumActivityByColor) {
                binding.tvSelectImage.setTextColor(ColorUtil(it).getActive())
            }
        }
    }
}
