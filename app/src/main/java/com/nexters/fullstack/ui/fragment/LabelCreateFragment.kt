package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelCreateBinding
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LabelCreateFragment : BaseFragment<FragmentLabelCreateBinding, LabelingViewModel>() {

    override val viewModel: LabelingViewModel by sharedViewModel()

    override val layoutRes: Int = R.layout.fragment_label_create

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
        }

        binding.palletLayout.setOnLabelClickListener = {
            viewModel.input.clickLabel(it)
        }
//        setOnObserve()
    }

//    private fun setOnObserve() {
//        with(viewModel.output) {
//            getLabelText().observe(this@LabelCreateFragment) { labelText ->
//                if (labelText.isNotEmpty()) {
//                    viewModel._didWriteLabelInfo.value = true
//                } else {
//                    labelText.ifEmpty {
//                        viewModel._didWriteLabelInfo.value = false
//                    }
//                }
//            }
//        }
//    }

    companion object {
        private var instance: LabelCreateFragment? = null
        fun getInstance(): LabelCreateFragment {
            return instance ?: LabelCreateFragment().also { instance = it }
        }
    }
}