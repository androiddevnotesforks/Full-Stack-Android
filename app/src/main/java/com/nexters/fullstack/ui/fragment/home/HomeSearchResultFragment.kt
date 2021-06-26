package com.nexters.fullstack.ui.fragment.home

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentHomeSearchSearchBinding
import com.nexters.fullstack.viewmodel.HomeSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchResultFragment : BaseFragment<FragmentHomeSearchSearchBinding, HomeSearchViewModel>(){
    override val layoutRes: Int = R.layout.fragment_home_search_search
    override val viewModel: HomeSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initView()
        initObserver()
    }

    fun initView(){
        // TODO add recyclerView for search result
    }

    fun initObserver(){
        // TODO  :  add search keyword and search result list observer
    }

    companion object {
        private var instance: HomeSearchResultFragment? = null
        fun getInstance(): HomeSearchResultFragment {
            if (instance == null) {
                instance = HomeSearchResultFragment()
            }
            return instance!!
        }
    }

}