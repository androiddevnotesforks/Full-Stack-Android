package com.nexters.fullstack.ui.activity

import android.os.Bundle
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabellingBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.viewmodel.LabelingViewModel
import com.nexters.fullstack.widget.CustomDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.ext.loadFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        initView()
//        bind {  }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
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

    override fun onSupportNavigateUp(): Boolean {
        RequestExitDialog().show(supportFragmentManager, "")
        return true
    }
}