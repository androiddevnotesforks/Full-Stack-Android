package com.nexters.fullstack.ui.activity.home

import android.os.Bundle
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeSearchBinding
import com.nexters.fullstack.ui.fragment.home.HomeSearchRecommendFragment
import com.nexters.fullstack.viewmodel.HomeSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchActivity : BaseActivity<ActivityHomeSearchBinding, HomeSearchViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_search
    override val viewModel: HomeSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initView()
        initListener()
    }

    private fun initView(){
        supportFragmentManager.beginTransaction().add(binding.frame.id, HomeSearchRecommendFragment.getInstance()).commit()
    }

    private fun initListener(){
        binding.ivCancel.setOnClickListener {
            finish()
        }
    }
}