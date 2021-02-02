package com.nexters.fullstack.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.base.BaseAdapter
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.ext.toPx
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

    private val adapter : MyLabelAdapter = MyLabelAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
        initOnClickListener()
        observe()
    }

    private fun initData(){
        when{
            intent?.action == Intent.ACTION_SEND
                    && intent.type?.startsWith(Constants.IMAGE_PREFIX) == true -> {
                (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { uri : Uri ->
                    viewModel.input.setImageUri(uri)
                }
            }
        }
    }

    private fun initView(){
        viewModel.imageUri.observe(this, {
            Glide.with(this)
                .load(it)
                .into(binding.ivScreenshot)
        })

        val spaceDecoration = SpaceItemDecoration(RV_SPACING_DP)
        adapter.addItems(viewModel.myLabels.value?: ArrayList())
        binding.rvLabel.adapter = adapter
        binding.rvLabel.addItemDecoration(spaceDecoration)
        binding.rvLabel.layoutManager = FlowLayoutManager()
        viewModel.myLabels.observe(this, {
            adapter.calDiff(it)
        })
    }

    private fun initOnClickListener(){
       binding.ivCancel.setOnClickListener {
            onBackPressed()
        }
        adapter.setItemClickListener { _, i, label ->

        }
    }

    private fun observe(){
    }

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
