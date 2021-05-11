package com.nexters.fullstack.ui.activity

import android.os.Bundle
import android.util.Log
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityAlbumActivitybyColorBinding
import com.nexters.fullstack.mapper.LocalImageMapper
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.source.LocalImageData
import com.nexters.fullstack.util.ColorUtil
import com.nexters.fullstack.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//todo 라벨 네임 컬러 정하기 리사이클러 뷰 데이터 init
class AlbumActivitybyColor : BaseActivity<ActivityAlbumActivitybyColorBinding, AlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_album_activityby_color
    override val viewModel: AlbumViewModel by viewModel()

    lateinit var labelName: LabelSource
    lateinit var labelColor: String
    lateinit var images: List<LocalImageData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setInitData()
        bind {
            vm = viewModel
            colorUtil = ColorUtil(viewModel.output.getLabelColor().value ?: labelColor)
        }
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

        val localImage = images.map(LocalImageMapper::fromDomain)
        with(viewModel.input) {
            setLabelName(labelName.name)
            setImages(localImage)
        }
    }

    private fun setObserve() {
        with(viewModel.output) {
            finishActivity().observe(this@AlbumActivitybyColor) {
                finish()
            }
        }
    }

}
