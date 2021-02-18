package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.nexters.fullstack.BR
import com.nexters.fullstack.Constants
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSelectBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.source.LocalFile
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
        Log.e(
            "fragment",
            arguments?.getParcelable<LocalFile>(Constants.LABEL_BUNDLE_KEY).toString()
        )
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
            binding.saveButton.setOnClickListener {
                clickLabelingButton()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        labelAdapter.selectedLabelClear()
    }

    companion object {
        private var instance: LabelSelectFragment? = null

        fun getInstance(localFileData: LocalFile? = null): LabelSelectFragment {
            if(localFileData == null) return LabelSelectFragment()
            return instance ?: LabelSelectFragment().apply {
                arguments =
                    Bundle().apply { putParcelable(Constants.LABEL_BUNDLE_KEY, localFileData) }
            }.also { instance = it }
        }
    }
}