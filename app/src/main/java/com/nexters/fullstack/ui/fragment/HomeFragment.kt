package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.FragmentHomeBinding
import com.nexters.fullstack.viewmodel.HomeMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeMainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_home
    override val viewModel: HomeMainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initView()
    }

    private fun initView(){

    }

    companion object {
        private var instance: HomeFragment? = null
        fun getInstance(): HomeFragment {
            if (instance == null) {
                instance = HomeFragment()
            }
            return HomeFragment()
        }
    }

}