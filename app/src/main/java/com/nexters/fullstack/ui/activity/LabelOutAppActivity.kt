package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.source.Label
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

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
        // TODO viewmodel.output.state()

        viewModel.imageUri.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .into(binding.ivScreenshot)
        })

        val spaceDecoration = VerticalSpaceItemDecoration(RV_SPACING)
        binding.rvLabel.adapter = MyLabelAdapter(this, viewModel.myLabels.value!!)
        binding.rvLabel.addItemDecoration(spaceDecoration)
        binding.rvLabel.layoutManager = FlowLayoutManager()

        viewModel.myLabels.observe(this, {
            it.let {
                // update
            }
        })
    }

    private fun initOnClickListener(){
       binding.ivCancel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observe(){
    }

    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.bottom = verticalSpaceHeight
            outRect.right = verticalSpaceHeight
        }
    }
    companion object{
        const val RV_SPACING = 10
    }
}
