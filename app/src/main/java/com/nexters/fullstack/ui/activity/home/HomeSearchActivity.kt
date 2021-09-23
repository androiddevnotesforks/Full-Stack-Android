package com.nexters.fullstack.ui.activity.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeSearchBinding
import com.nexters.fullstack.ui.fragment.home.HomeSearchRecommendFragment
import com.nexters.fullstack.ui.fragment.home.HomeSearchResultFragment
import com.nexters.fullstack.viewmodel.HomeSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchActivity : BaseActivity<ActivityHomeSearchBinding, HomeSearchViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_search
    override val viewModel: HomeSearchViewModel by viewModel()

    lateinit var currentFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {
            it.vm = viewModel
        }
        initView()
        initListener()
        initObserver()
    }

    private fun initView(){
        currentFragment = HomeSearchRecommendFragment.getInstance()
        supportFragmentManager.beginTransaction().add(binding.frame.id, currentFragment).commit()
    }

    private fun initListener(){
        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus) viewModel.setSearchMode()
            else viewModel.setRecommendMode()
        }

        binding.ivCancel.setOnClickListener {
            finish()
        }
    }

    private fun initObserver(){
        with(viewModel.state()){
            mode.observe(this@HomeSearchActivity,{
                currentFragment = when(it){
                    HomeSearchViewModel.ViewMode.RECOMMEND -> HomeSearchRecommendFragment.getInstance()
                    HomeSearchViewModel.ViewMode.SEARCH -> HomeSearchResultFragment.getInstance()
                }
                supportFragmentManager.beginTransaction().replace(binding.frame.id, currentFragment).commit()
            })
        }
    }

}