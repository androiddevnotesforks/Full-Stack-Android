package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.nexters.fullstack.MainActivity
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivitySplashBinding
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO viewmodel 임시
class SplashActivity : BaseActivity<ActivitySplashBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.activity_splash
    override val viewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
            finish()
        }, 1000L)
    }
}