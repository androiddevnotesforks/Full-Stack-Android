package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivitySearchLabelBinding
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchLabelActivity : BaseActivity<ActivitySearchLabelBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.activity_search_label

    override val viewModel: LabelingViewModel by viewModel()

    private val labelAdapter = MyLabelAdapter(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
        }

        onViewInit()
        onObserve()
    }

    private fun onViewInit() {
        with(binding) {
            rvMyLabel.run {
                adapter = labelAdapter
                layoutManager = FlexboxLayoutManager(this@SearchLabelActivity).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                }
            }
        }
    }

    private fun onObserve() {
        with(viewModel.output) {
            finish().observe(this@SearchLabelActivity) { value ->
                if (value != null) {
                    this@SearchLabelActivity.finish()
                }
            }
        }
    }
}