package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nexters.fullstack.BR
import com.nexters.fullstack.Constants
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.FragmentHomeBinding
import com.nexters.fullstack.ui.activity.HomeSearchActivity
import com.nexters.fullstack.ui.activity.LabelingActivity
import com.nexters.fullstack.ui.adapter.HomeMainParentAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.nexters.fullstack.viewmodel.HomeMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeMainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_home
    override val viewModel: HomeMainViewModel by viewModel()

    private val homeMainAdapter = HomeMainParentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind {
            setVariable(BR.vm, viewModel)
        }
        initView()
        initObserver()
        initListener()
    }

    private fun initView(){
        binding.rvParent.run {
            adapter = homeMainAdapter
            addItemDecoration(SpaceBetweenRecyclerDecoration(vertical = VERTICAL_SPACING))
        }
    }

    private fun initObserver(){
        viewModel.state().screenshotGroups.observe(viewLifecycleOwner, {
            homeMainAdapter.addItems(it)
        })
    }

    private fun initListener(){
        homeMainAdapter.setItemClickListener { view, i, homeList ->
            val intent = Intent(this.context, HomeSearchActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val VERTICAL_SPACING = 10

        private var instance: HomeFragment? = null
        fun getInstance(): HomeFragment {
            if (instance == null) {
                instance = HomeFragment()
            }
            return HomeFragment()
        }
    }

}