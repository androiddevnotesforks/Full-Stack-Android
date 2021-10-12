package com.nexters.fullstack.ui.activity.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.nexters.fullstack.util.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityDetailAlbumBinding
import com.nexters.fullstack.domain.entity.FileImageEntity
import com.nexters.fullstack.domain.entity.ImageEntity
import com.nexters.fullstack.presentaion.viewmodel.detail.DetailAlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAlbumActivity : BaseActivity<ActivityDetailAlbumBinding, DetailAlbumViewModel>() {

    override val layoutRes: Int = R.layout.activity_detail_album
    override val viewModel: DetailAlbumViewModel by viewModel<DetailAlbumViewModel>()
    private val localImage: FileImageEntity?
        get() = intent.getSerializableExtra(Constants.DETAIL_IMAGE) as? FileImageEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            it.localImageData = localImage
            it.viewModels = viewModel
            it.executePendingBindings()
        }

        setObserve()

        setOnClickListener()

        viewModel.fetchImage(localImage?.id ?: throw IllegalAccessException("not found id"))
    }

    private fun setObserve() {
        with(viewModel.output) {
            finish().observe(this@DetailAlbumActivity) {
                this@DetailAlbumActivity.finish()
            }
            getIsLocalContain().observe(this@DetailAlbumActivity) { contain ->
                val drawable = ContextCompat.getDrawable(
                    this@DetailAlbumActivity,
                    if (contain) R.drawable.ic_ico_already_heart else R.drawable.ic_ico_heart
                )

                binding.ivFavorite.setImageDrawable(drawable)
            }
        }
    }

    private fun setOnClickListener() {
        binding.frameFavorite.setOnClickListener {
            viewModel.input.favorite(localImage?.id ?: throw IllegalAccessException("not found id"))
        }
    }
}
