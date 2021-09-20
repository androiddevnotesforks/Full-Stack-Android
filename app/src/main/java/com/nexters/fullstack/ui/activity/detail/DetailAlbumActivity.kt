package com.nexters.fullstack.ui.activity.detail

import android.os.Bundle
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityDetailAlbumBinding
import com.nexters.fullstack.domain.source.data.LocalImageDomain
import com.nexters.fullstack.viewmodel.detail.DetailAlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAlbumActivity : BaseActivity<ActivityDetailAlbumBinding, DetailAlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_detail_album
    override val viewModel: DetailAlbumViewModel by viewModel<DetailAlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val localImage = intent.getSerializableExtra(Constants.DETAIL_IMAGE) as? LocalImageDomain

        bind {
            it.localImageData = localImage
            it.viewModels = viewModel
            it.executePendingBindings()
        }

        setObserve()
    }

    private fun setObserve() {
        with(viewModel.output) {
            finish().observe(this@DetailAlbumActivity) {
                this@DetailAlbumActivity.finish()
            }
        }
    }
}
