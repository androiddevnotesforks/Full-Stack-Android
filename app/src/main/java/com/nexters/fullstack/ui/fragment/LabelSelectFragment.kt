package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSelectBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.source.ViewState
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LabelSelectFragment : BaseFragment<FragmentLabelSelectBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_select
    override val viewModel: LabelingViewModel by sharedViewModel()
    private val labelAdapter = MyLabelAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
        }

        setOnInitView()
        setInitOnClickListener()
    }

    private fun setOnInitView() {
        binding.rvLabel.adapter = labelAdapter
        binding.rvLabel.addItemDecoration(SpaceBetweenRecyclerDecoration(vertical = 10))
    }

    private fun setInitOnClickListener() {
        with(viewModel.input) {
            binding.tvAddLabel.setOnClickListener {
                clickAppbar(ViewState.Add)
            }
        }
    }

    companion object {
        private var instance: LabelSelectFragment? = null

        fun getInstance(): LabelSelectFragment {
            return instance ?: LabelSelectFragment().also { instance = it }
        }
    }
}