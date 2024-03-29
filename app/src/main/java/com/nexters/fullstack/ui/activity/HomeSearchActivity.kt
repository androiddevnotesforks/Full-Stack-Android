package com.nexters.fullstack.ui.activity

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeSearchBinding
import com.nexters.fullstack.presentaion.viewmodel.HomeSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchActivity : BaseActivity<ActivityHomeSearchBinding, HomeSearchViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_search
    override val viewModel: HomeSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            it.vm = viewModel
        }
        initListener()
    }

    private fun initView() {

    }

    private fun initListener() {
        binding.ivCancel.setOnClickListener {
            finish()
        }
    }
}