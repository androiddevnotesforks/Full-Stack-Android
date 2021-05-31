package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentHomeSearchSearchBinding
import com.nexters.fullstack.viewmodel.HomeSearchSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchResultFragment : BaseFragment<FragmentHomeSearchSearchBinding, HomeSearchSearchViewModel>(){
    override val layoutRes: Int = R.layout.fragment_home_search_search
    override val viewModel: HomeSearchSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
    }
}