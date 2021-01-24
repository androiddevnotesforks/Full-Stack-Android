package com.nexters.fullstack.ui.fragment.label

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSearchBinding
import com.nexters.fullstack.R

class LabelSearchFragment : BaseFragment<FragmentLabelSearchBinding>() {

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