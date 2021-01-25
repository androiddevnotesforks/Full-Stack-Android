package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelCreateBinding
import com.nexters.fullstack.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelCreateFragment : BaseFragment<FragmentLabelCreateBinding, ViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_create

    override val viewModel: ViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private var instance: LabelCreateFragment? = null
        fun getInstance(): LabelCreateFragment {
            return instance ?: LabelCreateFragment().also { instance = it }
        }
    }
}