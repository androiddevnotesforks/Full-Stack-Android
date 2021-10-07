package com.nexters.fullstack.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.FragmentHomeBinding
import com.nexters.fullstack.ui.activity.home.HomeScreenshotActivity
import com.nexters.fullstack.ui.activity.home.HomeSearchActivity
import com.nexters.fullstack.ui.activity.SettingActivity
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
        homeMainAdapter.setItemClickListener { _, position, data ->
            val intent : Intent
            if(position == 0){
                intent = Intent(this.context, HomeSearchActivity::class.java)
            }
            else{
                intent = Intent(this.context, HomeScreenshotActivity::class.java)
                intent.putExtra(LIST_KEY, data)
            }
            startActivity(intent)
        }

        binding.ivProfile.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }
    }

    companion object {
        const val VERTICAL_SPACING = 10
        const val LIST_KEY = "home_list"

        fun getInstance(): HomeFragment {
            val instance = HomeFragment().apply { bundleOf("tag" to "homeFragment") }
            return instance
        }
    }

}