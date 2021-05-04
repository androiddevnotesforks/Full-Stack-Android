package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeScreenshotBinding
import com.nexters.fullstack.source.HomeList
import com.nexters.fullstack.source.HomeScreenshot
import com.nexters.fullstack.ui.adapter.HomeScreenshotAdapter
import com.nexters.fullstack.viewmodel.HomeScreenshotViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeScreenshotActivity : BaseActivity<ActivityHomeScreenshotBinding, HomeScreenshotViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_screenshot
    override val viewModel: HomeScreenshotViewModel by viewModel()

    private val screenshotAdapter = HomeScreenshotAdapter(HomeScreenshotViewModel.Mode.DEFAULT)
    private val imagePickerAdapter = HomeScreenshotAdapter(HomeScreenshotViewModel.Mode.SELECTION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initView()
        initData()
        initListener()
        initObserver()
        Log.e("init mode", viewModel.state().mode.value!!.name)
    }

    private fun initView(){
        binding.rvImages.adapter = screenshotAdapter

    }

    private fun initData(){
        intent.getParcelableExtra<HomeList>(LIST_KEY)?.let {
            with(viewModel.state()){
                title.value = it.title
                images.value = it.images
            }
        }
    }

    private fun initListener(){
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
            tvSelection.setOnClickListener {
                viewModel.changeMode()
                Log.e("current mode ", viewModel.state().mode.value!!.name)
            }
            ivCancel.setOnClickListener {
                viewModel.changeMode()
                Log.e("current mode ", viewModel.state().mode.value!!.name)
            }
            imagePickerAdapter.setItemClickListener { _, _, _ ->
                viewModel.updateSelected(imagePickerAdapter.getSelectedList())
            }
            screenshotAdapter.setItemClickListener { _, _, data ->
                val intent = Intent(this@HomeScreenshotActivity, ScreenshotDetailActivity::class.java)
                intent.putExtra(IMAGE_KEY, data)
                startActivity(intent)
            }
        }
    }

    private fun initObserver(){
        with(viewModel.state()){
            images.observe(this@HomeScreenshotActivity, {
                screenshotAdapter.addItems(it)
                imagePickerAdapter.addItems(it)
            })
            mode.observe(this@HomeScreenshotActivity, {
                when(it){
                    HomeScreenshotViewModel.Mode.DEFAULT -> binding.rvImages.adapter = screenshotAdapter
                    HomeScreenshotViewModel.Mode.SELECTION -> binding.rvImages.adapter = imagePickerAdapter
                }
            })
        }
    }

    companion object{
        const val LIST_KEY = "home_list"
        const val IMAGE_KEY = "image"
    }
}