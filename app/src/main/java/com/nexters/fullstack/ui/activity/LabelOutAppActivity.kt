package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.ext.toPx
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.ui.adapter.SelectedLabelAdapter
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import com.xiaofeng.flowlayoutmanager.Alignment
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

    private val myLabelAdapter : MyLabelAdapter = MyLabelAdapter()
    private val selectedLabelAdapter : SelectedLabelAdapter = SelectedLabelAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initData()
        initView()
        initOnClickListener()
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
            val spaceDecoration = SpaceItemDecoration(RV_SPACING_DP)
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

    private fun initOnClickListener(){
       binding.ivCancel.setOnClickListener {
            onBackPressed()
        }
        binding.ivCancel.setOnClickListener {
            viewModel.completeLabeling()
            // TODO finish activity and show toast
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
                myLabelAdapter.calDiff(it as MutableList<Label>)
            })
            selectedLabels.observe(this@LabelOutAppActivity, {
                selectedLabelAdapter.calDiff(it as MutableList<Label>)
                binding.rvSelectedLabel.scrollToPosition(0)
            })
        }
    }

    inner class SpaceItemDecoration(private val space_dp: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = space_dp.toPx
            outRect.right = space_dp.toPx
        }
    }

    companion object{
        const val RV_SPACING_DP = 10
    }
}
