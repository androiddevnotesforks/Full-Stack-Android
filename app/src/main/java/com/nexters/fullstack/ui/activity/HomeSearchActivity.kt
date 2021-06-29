package com.nexters.fullstack.ui.activity

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeSearchBinding
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
        initListener()
    }

    private fun initView(){

    }

    private fun initListener(){
        binding.tvCancel.setOnClickListener {
            finish()
        }
        binding.etSearch.addTextChangedListener {
            if(it != null) {
                if(it.toString().isEmpty()) {
                    binding.ivClear.visibility = View.GONE
                } else {
                    binding.ivClear.visibility = View.VISIBLE
                }
            } else {
                binding.ivClear.visibility = View.GONE
            }
        }
    }
}