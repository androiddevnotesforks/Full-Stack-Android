package com.nexters.fullstack.ui.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.nexters.fullstack.BR
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityCreateLabelBinding
import com.nexters.fullstack.db.entity.UserLabel
import com.nexters.fullstack.source.LabelingState
import com.nexters.fullstack.source.MainMakeLabelSource
import com.nexters.fullstack.ui.widget.bottomsheet.mapper.mapToPallet
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateLabelActivity : BaseActivity<ActivityCreateLabelBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.activity_create_label

    override val viewModel: LabelingViewModel by viewModel()
    var viaLabelData: UserLabel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolbar()
        setIntiView()
        bind {
            it.vm = viewModel
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
        binding.tvLabeling.setOnClickListener {
            if (viaLabelData != null) {
                viewModel.input.clickSaveButton(LabelingState.UPDATE)
            } else {
                viewModel.input.clickSaveButton(LabelingState.CREATE)
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
    }

    private fun setIntiView() {
        viaLabelData = intent.getParcelableExtra(Constants.LABEL_MODIFY_KEY)

        if (viaLabelData != null) {
            binding.etLabelText.setText(viaLabelData!!.text)

            //커서 이동
            binding.etLabelText.setSelection(viaLabelData!!.text.length)
            viewModel.onCreateView(
                MainMakeLabelSource(
                    viaLabelData!!.text,
                    viaLabelData!!.mapToPallet()
                )
            )
            viewModel.input.setDidLabelingState(true)
        }
        binding.palletLayout.setOnInitView(viaLabelData?.mapToPallet())
    }

    override fun onDestroy() {
        viaLabelData = null
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}