package com.nexters.fullstack.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabellingBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.ext.loadFragment
import com.nexters.fullstack.source.ViewState
import com.nexters.fullstack.ui.fragment.LabelCreateFragment
import com.nexters.fullstack.widget.RequestExitDialog
import com.nexters.fullstack.ui.fragment.LabelSelectFragment
import com.nexters.fullstack.ui.fragment.label.LabelSearchFragment

class LabelingActivity : BaseActivity<ActivityLabellingBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.activity_labelling
    override val viewModel: LabelingViewModel by viewModel()

    private val labelSelectFragment: LabelSelectFragment = LabelSelectFragment.getInstance()
    private val labelCreateFragment: LabelCreateFragment = LabelCreateFragment.getInstance()
    private val labelSearchFragment: LabelSearchFragment = LabelSearchFragment.getInstance()
    private var activeFragment: Fragment = labelSelectFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setOnClickListener()
        initToolbar()
        initView()
        observe()
        bind { }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
    }

    private fun setOnClickListener() {
        with(viewModel.input) {
            binding.addLabel.setOnClickListener { setViewState(ViewState.Add) }
            binding.searchLabel.setOnClickListener { setViewState(ViewState.Search) }
        }
    }

    private fun initView() {
        /**
         **   todo viewmodel 에서 클릭 시 state변경
         *   ex. State ( Init, Search, Add )
         *   observe -> State
         *   when(state) {
         *      is Init -> changeFragment()   todo binding.title.text = "라벨 추가"
         *      is Search -> changeFragment()
         *      ...
         *   }
         **/
        supportFragmentManager.loadFragment(
            binding.frame.id,
            labelSelectFragment,
            labelCreateFragment,
            labelSearchFragment
        )

        supportFragmentManager.beginTransaction().show(labelSelectFragment).commit()
    }

    private fun observe() {
        with(viewModel.output) {
            viewState().observe(this@LabelingActivity, Observer { viewState ->
                when (viewState) {
                    is ViewState.Selected -> changeFragment(activeFragment, labelSelectFragment)
                    is ViewState.Add -> changeFragment(activeFragment, labelCreateFragment)
                    is ViewState.Search -> changeFragment(activeFragment, labelSearchFragment)
                }
            })
        }
    }

    private fun changeFragment(oldFragment: Fragment, newFragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(oldFragment).show(newFragment).commit()
        activeFragment = newFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (viewModel.viewState.value != ViewState.Selected) {
            viewModel.setViewState(ViewState.Selected)
            false
        } else {
            RequestExitDialog().show(supportFragmentManager, "")
            true
        }
    }
}