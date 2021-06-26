package com.nexters.fullstack.ui.fragment.home

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentHomeSearchRecommendBinding
import com.nexters.fullstack.viewmodel.HomeSearchRecommendViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchRecommendFragment : BaseFragment<FragmentHomeSearchRecommendBinding, HomeSearchRecommendViewModel>() {
    override val layoutRes: Int = R.layout.fragment_home_search_recommend
    override val viewModel: HomeSearchRecommendViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initListener()
    }

    fun initListener(){
        binding.searchBar.setOnClickListener {
            parentFragmentManager.beginTransaction().hide(this).show(HomeSearchResultFragment.getInstance()).commit()
        }
    }

    companion object {
        private var instance: HomeSearchRecommendFragment? = null
        fun getInstance(): HomeSearchRecommendFragment {
            if (instance == null) {
                instance = HomeSearchRecommendFragment()
            }
            return instance!!
        }
    }
}