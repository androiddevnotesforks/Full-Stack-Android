package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeSettingBinding
import com.nexters.fullstack.viewmodel.HomeSettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingActivity : BaseActivity<ActivityHomeSettingBinding, HomeSettingViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_setting
    override val viewModel: HomeSettingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}