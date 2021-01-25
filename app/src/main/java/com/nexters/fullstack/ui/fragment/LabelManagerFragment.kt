package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelManagerBinding
import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.ui.activity.LabelingActivity

class LabelManagerFragment : BaseFragment<FragmentLabelManagerBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_manager
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        observer()
    }

    private fun setOnClickListener() {
        binding.labeledButton.setOnClickListener {
            viewModel.setButtonState(LabellingState.Approve)
        }
        binding.skipButton.setOnClickListener {
            viewModel.setButtonState(LabellingState.Rejected)
        }
    }

    private fun observer() {
        viewModel.labellingState.observe(
            this@LabelManagerFragment.viewLifecycleOwner,
            Observer { state ->
                if (viewModel.isLabellingStart(state)) {
                    startActivity(
                        Intent(
                            this@LabelManagerFragment.context,
                            LabelingActivity::class.java
                        )
                    )
                } else {
                    //todo 맨 뒤로 이동.
                }
            })
    }

    companion object {
        private var instance: LabelManagerFragment? = null
        fun getInstance(): LabelManagerFragment {
            if (instance == null) {
                instance = LabelManagerFragment()
            }
            return LabelManagerFragment()
        }
    }
}