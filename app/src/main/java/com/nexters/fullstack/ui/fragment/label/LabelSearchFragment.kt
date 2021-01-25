package com.nexters.fullstack.ui.fragment.label

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSearchBinding
import com.nexters.fullstack.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelSearchFragment : BaseFragment<FragmentLabelSearchBinding, ViewModel>() {

    override val viewModel: ViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_label_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private var instance: LabelSearchFragment? = null
        fun getInstance(): LabelSearchFragment {
            return instance ?: LabelSearchFragment().also { instance = it }
        }
    }
}