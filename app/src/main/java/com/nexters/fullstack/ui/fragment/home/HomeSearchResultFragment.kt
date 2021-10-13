package com.nexters.fullstack.ui.fragment.home

import android.os.Bundle
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentHomeSearchSearchBinding
import com.nexters.fullstack.ui.adapter.HomeSearchAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.nexters.fullstack.presentaion.viewmodel.HomeSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSearchResultFragment : BaseFragment<FragmentHomeSearchSearchBinding, HomeSearchViewModel>(){
    override val layoutRes: Int = R.layout.fragment_home_search_search
    override val viewModel: HomeSearchViewModel by viewModel()

    private val searchResultAdapter = HomeSearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            this.vm = viewModel
        }
        initView()
        initObserver()
    }

    fun initView(){
        binding.rvSearchResult.run {
            adapter = searchResultAdapter
            addItemDecoration(SpaceBetweenRecyclerDecoration(HomeSearchRecommendFragment.RV_SPACING_DP, HomeSearchRecommendFragment.RV_SPACING_DP))
            layoutManager = FlexboxLayoutManager(this@HomeSearchResultFragment.context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
            }
            setHasFixedSize(true)
        }
    }

    fun initObserver(){
        viewModel.state().searchResult.observe(viewLifecycleOwner, {
            searchResultAdapter.addItems(it)
        })
    }

    companion object {
        const val RV_SPACING_DP = 5
        private var instance: HomeSearchResultFragment? = null
        fun getInstance(): HomeSearchResultFragment {
            if (instance == null) {
                instance = HomeSearchResultFragment()
            }
            return instance!!
        }
    }

}