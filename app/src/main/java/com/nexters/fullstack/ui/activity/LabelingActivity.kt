package com.nexters.fullstack.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nexters.fullstack.util.Constants
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.ActivityLabelingBinding
import com.nexters.fullstack.presentaion.viewmodel.LabelingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.util.extension.loadFragment
import com.nexters.fullstack.util.extension.removeFragment
import com.nexters.fullstack.presentaion.model.ViewState
import com.nexters.fullstack.ui.widget.RequestExitDialog
import com.nexters.fullstack.ui.fragment.LabelSelectFragment

class LabelingActivity : BaseActivity<ActivityLabelingBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.activity_labeling
    override val viewModel: LabelingViewModel by viewModel()

    private val dialog: RequestExitDialog by lazy {
        RequestExitDialog()
    }

    private lateinit var labelSelectFragment: LabelSelectFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        labelSelectFragment =
            LabelSelectFragment.getInstance(intent.getParcelableExtra(Constants.LABEL_BUNDLE_KEY))
        activeFragment = labelSelectFragment

        initView()
        setOnClickListener()
        initToolbar()
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
            binding.addLabel.setOnClickListener { clickAppbar(ViewState.Add) }
            binding.searchLabel.setOnClickListener { clickAppbar(ViewState.Search) }
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
            labelSelectFragment
        )

        supportFragmentManager.beginTransaction().show(labelSelectFragment).commit()
    }

    private fun observe() {
        with(viewModel.output) {
            viewState().observe(this@LabelingActivity) { viewState ->
                when (viewState) {
                    is ViewState.Selected -> changeFragment(activeFragment, labelSelectFragment)
                    is ViewState.Add -> startActivity(
                        Intent(
                            this@LabelingActivity,
                            CreateLabelActivity::class.java
                        )
                    )
                    is ViewState.Search -> startActivity(
                        Intent(
                            this@LabelingActivity,
                            SearchLabelActivity::class.java
                        )
                    )
                }
            }
            finish().observe(this@LabelingActivity) {
                if (it != null) {
                    setResult(Activity.RESULT_OK)
                    this@LabelingActivity.finish()
                }
            }
        }
    }

    override fun onDestroy() {
        supportFragmentManager.removeFragment(
            labelSelectFragment
        )
        super.onDestroy()
    }

    private fun changeFragment(oldFragment: Fragment, newFragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(oldFragment).show(newFragment).commit()
        activeFragment = newFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        dialog.show(supportFragmentManager, "")
        return true
    }
}
