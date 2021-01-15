package com.nexters.fullstack

import android.os.Bundle
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind {  }
    }
}