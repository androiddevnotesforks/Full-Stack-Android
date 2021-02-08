package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSelectBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.source.ViewState
import com.nexters.fullstack.ui.adapter.LabelingSelectAdapter
import com.nexters.fullstack.ui.decoration.LabelRecyclerDecoration
import com.nexters.fullstack.viewmodel.LabelingViewModel
import com.nexters.fullstack.widget.RequestExitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelSelectFragment : BaseFragment<FragmentLabelSelectBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_select
    override val viewModel: LabelingViewModel by viewModel()
    private val labelAdapter = LabelingSelectAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
        }

//        labelAdapter.addItems(listOf(LabelSource(LabelSource.LIST, "#EC9147", "테스트")))

        binding.rvLabel.adapter = labelAdapter
        binding.rvLabel.addItemDecoration(LabelRecyclerDecoration())

        binding.tvAddLabel.setOnClickListener {
            viewModel.input.clickAppbar(ViewState.Add)
        }
    }

    companion object {
        private var instance: LabelSelectFragment? = null

        fun getInstance(): LabelSelectFragment {
            return instance ?: LabelSelectFragment().also { instance = it }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        RequestExitDialog().show(supportFragmentManager, "")
//        return true
//    }
}