package com.nexters.fullstack.ui.activity

import android.os.Bundle
import android.util.Log
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityAlbumActivitybyColorBinding
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.util.ColorUtil
import com.nexters.fullstack.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumActivitybyColor : BaseActivity<ActivityAlbumActivitybyColorBinding, AlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_album_activityby_color
    override val viewModel: AlbumViewModel by viewModel()

    lateinit var labelName: LabelSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setInitData()
        bind {
            vm = viewModel
        }
    }

    private fun setInitData() {
        labelName = intent.getParcelableExtra(Constants.LABEL) ?: LabelSource(name = "N/A", color = "Yellow")
        with(viewModel.input) {
            setLabelName(labelName.name)
        }
    }

    private fun setObserve() {

    }
}
