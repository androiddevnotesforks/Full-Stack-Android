package com.nexters.fullstack.ui.activity

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.nexters.fullstack.Constants.SETTING_KEY
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityHomeSettingBinding
import com.nexters.fullstack.ext.setSettingEvents
import com.nexters.fullstack.viewmodel.HomeSettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingActivity : BaseActivity<ActivityHomeSettingBinding, HomeSettingViewModel>() {
    override val layoutRes: Int = R.layout.activity_home_setting
    override val viewModel: HomeSettingViewModel by viewModel()

    private val firebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
        initFirebaseEvent()
    }

    private fun initListener(){
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun initFirebaseEvent() {
        binding.tvPremium.setOnClickListener(::onPostFirebaseAnalytics)
        binding.listReview.setOnClickListener(::onPostFirebaseAnalytics)
        binding.listRecommendation.setOnClickListener(::onPostFirebaseAnalytics)
        binding.listGuide.setOnClickListener(::onPostFirebaseAnalytics)
        binding.listLabelClear.setOnClickListener(::onPostFirebaseAnalytics)
    }

    private fun onPostFirebaseAnalytics(view: View) {
        firebaseAnalytics.setSettingEvents(view, bundleOf(SETTING_KEY to view))
    }
}