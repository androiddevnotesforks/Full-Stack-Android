package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nexters.fullstack.Constants
import com.nexters.fullstack.NotFoundViewState
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.ext.hideKeyboard
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
    private val recentlyLabelAdapter : MyLabelAdapter = MyLabelAdapter()
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

            val spaceDecoration = SpaceBetweenRecyclerDecoration(RV_SPACING_DP, RV_SPACING_DP)
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

            recentlyLabelAdapter.addItems(viewModel.state().recentlySearch.value ?: ArrayList())
            searchResultAdapter.addItems(viewModel.state().searchResult.value ?: ArrayList())
        }
    }

    private fun initListener(){
        with(binding){
            ivCancel.setOnClickListener {
                onBackPressed()
            }
            ivBack.setOnClickListener {
                viewModel.setViewState(LabelOutAppViewModel.ViewState.MY_LABEL)
            }
            ivClear.setOnClickListener {
                viewModel.clearSearchKeyword()
            }
            tvDone.setOnClickListener {
                viewModel.completeLabeling()
                // TODO finish activity and show toast
            }
//            etSearch.setOnClickListener {
//                containerAppbar.setExpanded(false)
//            }
            etSearch.setOnFocusChangeListener { _, isFocused ->
                if(isFocused){
                    containerAppbar.setExpanded(false)
                }
                else{
                    etSearch.hideKeyboard()
                }
            }
            etSearch.setOnEditorActionListener { textView, action, keyEvent ->
                var handled = false
                if(action == EditorInfo.IME_ACTION_DONE){
                    etSearch.clearFocus()
                    handled = true
                }
                handled
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
                if(it == ""){
                    viewModel.setViewState(LabelOutAppViewModel.ViewState.RECENT_LABEL)
                }
                else{
                    viewModel.setViewState(LabelOutAppViewModel.ViewState.SEARCH_RESULT)
                    // TODO 검색 결과 recycler view list update
                }
            })
            viewState.observe(this@LabelOutAppActivity, {
                when(it){
                    LabelOutAppViewModel.ViewState.MY_LABEL -> {
                        binding.rvLabel.adapter = myLabelAdapter
                        binding.containerAppbar.setExpanded(true)
                        binding.etSearch.hideKeyboard()
                        binding.etSearch.clearFocus()
                        viewModel.clearSearchKeyword()
                    }
                    LabelOutAppViewModel.ViewState.RECENT_LABEL -> {
                        binding.rvLabel.adapter = recentlyLabelAdapter
                    }
                    LabelOutAppViewModel.ViewState.SEARCH_RESULT -> {
                        binding.rvLabel.adapter = searchResultAdapter
                    }
                    else -> throw NotFoundViewState
                }
            })
        }
    }



    companion object{
        const val RV_SPACING_DP = 5
    }
}
