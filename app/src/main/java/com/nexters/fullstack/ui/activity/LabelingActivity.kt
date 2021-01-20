package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabellingBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.widget.CustomDialog

class LabelingActivity : BaseActivity<ActivityLabellingBinding>() {
    override val layoutRes: Int = R.layout.activity_labelling

    private val dialog by lazy {
        CustomDialog.Builder(context = this, themeLayoutId = R.style.Transparent).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initDialog()
//        bind {  }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
    }

    private fun initDialog() {
        dialog.setOnCancelListener {
            dialog.dismiss()
            onSupportNavigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        dialog.show()
//        onBackPressed()
        return true
    }
}