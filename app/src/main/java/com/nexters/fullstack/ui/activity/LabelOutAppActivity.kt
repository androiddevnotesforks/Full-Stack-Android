package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.ui.adapter.SelectedLabelAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

    private val myLabelAdapter : MyLabelAdapter = MyLabelAdapter()
    private val selectedLabelAdapter : SelectedLabelAdapter = SelectedLabelAdapter()
    private val recentlySearchAdapter : MyLabelAdapter = MyLabelAdapter()
    private val searchResultAdapter : MyLabelAdapter = MyLabelAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initData()
        initView()
        initListener()
        initObserver()
    }

    private fun initData(){
        when{
            intent?.action == Intent.ACTION_SEND
                    && intent.type?.startsWith(Constants.IMAGE_PREFIX) == true -> {
                (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { uri: Uri ->
                    viewModel.loadImage(uri)
                }
            }
        }
    }

    private fun initView() {
        with(viewModel.state()) {
            imageUri.observe(this@LabelOutAppActivity, {
                Glide.with(this@LabelOutAppActivity)
                    .load(it)
                    .into(binding.ivScreenshot)
            })
        }

        with(binding){

            val spaceDecoration = SpaceBetweenRecyclerDecoration(vertical = RV_SPACING_DP)
            rvLabel.run {
                myLabelAdapter.addItems(viewModel.state().myLabels.value ?: ArrayList())
                adapter = myLabelAdapter
                rvLabel.addItemDecoration(spaceDecoration)
                layoutManager = FlexboxLayoutManager(this@LabelOutAppActivity).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                }
                setHasFixedSize(true)
            }

            selectedLabelAdapter.addItems(viewModel.state().selectedLabels.value ?: ArrayList())
            rvSelectedLabel.adapter = selectedLabelAdapter
            rvSelectedLabel.addItemDecoration(spaceDecoration)
        }
    }

    private fun initListener(){
        with(binding){
            ivCancel.setOnClickListener {
                onBackPressed()
            }
            tvDone.setOnClickListener {
                viewModel.completeLabeling()
                // TODO finish activity and show toast
            }
            etSearch.setOnClickListener {
                containerAppbar.setExpanded(false)
            }
            etSearch.setOnFocusChangeListener { _, isFocused ->
                if(isFocused){
                    viewModel.state().isSearchMode.value = true
                }
            }
        }
        myLabelAdapter.setItemClickListener { _, i, _ ->
            viewModel.selectLabel(i)
        }
        selectedLabelAdapter.setItemClickListener { _, i, _ ->
            viewModel.deselectLabel(i)
        }
    }

    private fun initObserver(){
        with(viewModel.state()){
            myLabels.observe(this@LabelOutAppActivity, {
                myLabelAdapter.calDiff(it as MutableList<LabelSource>)
            })
            selectedLabels.observe(this@LabelOutAppActivity, {
                selectedLabelAdapter.calDiff(it as MutableList<LabelSource>)
                binding.rvSelectedLabel.scrollToPosition(0)
            })
            searchKeyword.observe(this@LabelOutAppActivity, {
                // TODO 검색 결과 recycler view list update
            })
            isSearchMode.observe(this@LabelOutAppActivity, {
                // TODO adapter change
                if(it){
                    binding.containerAppbar.setExpanded(false)
                }
                else{

                }
            })
        }
    }

    companion object{
        const val RV_SPACING_DP = 10
    }
}
