package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
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
//    <  using shared preference  >
//    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        <  using shared preference  >
//        sharedPref = this.getPreferences(MODE_PRIVATE)
        initView()
        initListener()
    }

    private fun initView(){
        binding.pager.adapter = PagerAdapter(this)
        setIndicator(0)
    }

    private fun initListener(){
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
            tvSkip.setOnClickListener {
                startMainActivity()
            }

            pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setIndicator(position)
                }
            })
        }
    }

    private fun setIndicator(position: Int){
        with(binding){
            indicator1.isSelected = false
            indicator2.isSelected = false
            indicator3.isSelected = false

            when(position){
                0 -> binding.indicator1.isSelected = true
                1 -> binding.indicator2.isSelected = true
                2 -> binding.indicator3.isSelected = true
            }
        }
    }

    private fun startMainActivity(){
//        <  using shared preference  >
//        sharedPref.edit().putBoolean(Constants.FIRST_LOADING, false).apply()
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