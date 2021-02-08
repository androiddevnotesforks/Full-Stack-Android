package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSelectBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.viewmodel.LabelingViewModel
import com.nexters.fullstack.widget.RequestExitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelSelectFragment : BaseFragment<FragmentLabelSelectBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_select
    override val viewModel: LabelingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
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