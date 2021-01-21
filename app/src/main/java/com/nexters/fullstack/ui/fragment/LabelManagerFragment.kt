package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelManagerBinding
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.ui.activity.LabelingActivity

class LabelManagerFragment() : BaseFragment<FragmentLabelManagerBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_manager
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.labeledButton.setOnClickListener {
            startActivity(Intent(activity, LabelingActivity::class.java))
        }
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