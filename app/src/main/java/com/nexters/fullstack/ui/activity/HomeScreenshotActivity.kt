package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeScreenshotBinding
import com.nexters.fullstack.ui.adapter.HomeScreenshotAdapter
import com.nexters.fullstack.viewmodel.HomeScreenshotViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeScreenshotActivity : BaseActivity<ActivityHomeScreenshotBinding, HomeScreenshotViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_screenshot
    override val viewModel: HomeScreenshotViewModel by viewModel()

    private val screenshotAdapter = HomeScreenshotAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initView()
        initData()
        initListener()
        initObserver()
    }

    private fun initView(){
        binding.rvImages.adapter = screenshotAdapter
    }

    private fun initData(){
        viewModel.state().title.value = intent.getStringExtra(TITLE_KEY)?:""
        // TODO init imageUrl list
    }

    private fun initListener(){
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initObserver(){
        viewModel.state().images.observe(this, {

        })
    }

    companion object{
        const val TITLE_KEY = "title"
        const val LIST_IMAGES_KEY = "images"
    }
}