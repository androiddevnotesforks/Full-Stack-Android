package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSelectBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.widget.RequestExitDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelSelectFragment : BaseFragment<FragmentLabelSelectBinding, ViewModel>() {

    override val viewModel: ViewModel by viewModel()
    override val layoutRes: Int = R.layout.fragment_label_select

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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