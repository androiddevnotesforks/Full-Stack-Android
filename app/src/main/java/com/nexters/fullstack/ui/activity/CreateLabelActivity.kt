package com.nexters.fullstack.ui.activity

import android.app.Activity
import android.os.Bundle
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityCreateLabelBinding
import com.nexters.fullstack.source.ViewState
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateLabelActivity : BaseActivity<ActivityCreateLabelBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.activity_create_label

    override val viewModel: LabelingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        setIntiView()
        bind {
            setVariable(BR.vm, viewModel)
        }


        setOnClickListener()
        onObserve()
    }

    private fun onObserve() {
        with(viewModel.output) {
            finish().observe(this@CreateLabelActivity) {
                if (it != null) {
                    setResult(Activity.RESULT_OK)
                    this@CreateLabelActivity.finish()
                }
            }
        }
    }

    private fun setOnClickListener() {
        binding.palletLayout.setOnLabelClickListener = {
            viewModel.input.clickLabel(it)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
    }

    private fun setIntiView() {
        binding.palletLayout.setOnInitView()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}