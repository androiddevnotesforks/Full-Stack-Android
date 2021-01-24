package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelCreateBinding
import com.nexters.fullstack.R

class LabelCreateFragment : BaseFragment<FragmentLabelCreateBinding>() {
    override val layoutRes: Int = R.layout.fragment_label_create

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