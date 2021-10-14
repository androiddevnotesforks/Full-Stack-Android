package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.bumptech.glide.Glide
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityScreenshotDetailBinding
import com.nexters.fullstack.presentaion.viewmodel.ScreenshotDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScreenshotDetailActivity : BaseActivity<ActivityScreenshotDetailBinding, ScreenshotDetailViewModel>() {
    override val layoutRes: Int = R.layout.activity_screenshot_detail
    override val viewModel: ScreenshotDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initObserver()
    }

    private fun initData(){
        viewModel.state().image.value = intent.getParcelableExtra(IMAGE_KEY)
    }

    private fun initObserver(){
        viewModel.state().image.observe(this, {
            Glide.with(this@ScreenshotDetailActivity)
                .load(it.imageUrl)
                .into(binding.ivImage)
        })
    }

    companion object{
        const val IMAGE_KEY = "image"
    }

}