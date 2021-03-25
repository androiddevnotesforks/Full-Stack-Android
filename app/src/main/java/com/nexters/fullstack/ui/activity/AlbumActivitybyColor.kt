package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityAlbumActivitybyColorBinding
import com.nexters.fullstack.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumActivitybyColor : BaseActivity<ActivityAlbumActivitybyColorBinding, AlbumViewModel>() {
    override val layoutRes: Int = R.layout.activity_album_activityby_color

    override val viewModel: AlbumViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {  }
    }
}