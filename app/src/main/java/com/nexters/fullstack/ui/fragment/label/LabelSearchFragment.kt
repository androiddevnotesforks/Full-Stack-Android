package com.nexters.fullstack.ui.fragment.label

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSearchBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.source.ActivityResultData
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelSearchFragment : BaseFragment<FragmentLabelSearchBinding, LabelingViewModel>() {

    override val viewModel: LabelingViewModel by sharedViewModel()
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

    override fun onActivityResult(activityResultData: ActivityResultData) {

    }
}