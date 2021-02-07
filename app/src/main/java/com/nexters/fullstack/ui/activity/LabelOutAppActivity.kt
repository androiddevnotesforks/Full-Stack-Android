package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.ext.toPx
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.ui.adapter.SelectedLabelAdapter
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

    private val myLabelAdapter : MyLabelAdapter = MyLabelAdapter()
    private val selectedAdapter : SelectedLabelAdapter = SelectedLabelAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
//        initOnClickListener()
//        observe()
    }

    private fun initData(){
        when{
            intent?.action == Intent.ACTION_SEND
                    && intent.type?.startsWith(Constants.IMAGE_PREFIX) == true -> {
                (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { uri : Uri ->
                    viewModel.input.loadImage(uri)
                }
            }
        }
    }

    private fun initView(){
        with(viewModel.output.state()){
            imageUri.observe(this@LabelOutAppActivity, {
                Glide.with(this@LabelOutAppActivity)
                    .load(it)
                    .into(binding.ivScreenshot)
            })
        }
//
//        val spaceDecoration = SpaceItemDecoration(RV_SPACING_DP)
//        myLabelAdapter.addItems(viewModel.myLabels.value?: ArrayList())
//        binding.rvLabel.adapter = myLabelAdapter
//        binding.rvLabel.addItemDecoration(spaceDecoration)
//        binding.rvLabel.layoutManager = FlowLayoutManager()
//
//        selectedAdapter.addItems(viewModel.selectedLabels.value?: ArrayList())
//        binding.rvSelectedLabel.adapter = selectedAdapter
//        binding.rvSelectedLabel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
//
//    private fun initOnClickListener(){
//       binding.ivCancel.setOnClickListener {
//            onBackPressed()
//        }
//        myLabelAdapter.setItemClickListener { _, i, _ ->
//            viewModel.output.selectLabel(i)
//        }
//    }
//
//    private fun observe(){
//        with(viewModel){
//            myLabels.observe(this@LabelOutAppActivity, {
//                myLabelAdapter.calDiff(it)
//            })
//            selectedLabels.observe(this@LabelOutAppActivity, {
//
//            })
//        }
//    }

    inner class SpaceItemDecoration(private val space_dp: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = space_dp.toPx
            outRect.right = space_dp.toPx
        }
    }

    companion object{
        const val RV_SPACING_DP = 10
    }
}
