package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nexters.fullstack.MainActivity
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityOnboardingBinding
import com.nexters.fullstack.ui.fragment.OnBoardingFragment
import com.nexters.fullstack.util.PrefDataStoreManager
import com.nexters.fullstack.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding, OnBoardingViewModel>() {
    override val layoutRes: Int = R.layout.activity_onboarding
    override val viewModel: OnBoardingViewModel by viewModel()

    private val prefDataStoreManager = PrefDataStoreManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListner()
    }

    private fun initView(){
        binding.pager.adapter = PagerAdapter(this)
    }

    // TODO 건너뛰기 추가
    private fun initListner(){
        with(binding){
            tvButton.setOnClickListener {
                with(pager){
                    if(currentItem == PAGE_NUM-1){
                        startMainActivity()
                    }else{
                        currentItem += 1
                    }
                }
            }
        }
    }

    private fun startMainActivity(){
        CoroutineScope(Dispatchers.IO).launch {
            prefDataStoreManager.updateIsFirst(false)
            startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
        }
    }

    private inner class PagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa){
        override fun getItemCount(): Int = PAGE_NUM
        override fun createFragment(position: Int): Fragment = OnBoardingFragment.newInstance(position)
    }

    companion object{
        private const val PAGE_NUM = 3
    }
}