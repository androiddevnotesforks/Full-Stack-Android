package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.rvLabel.adapter = MyLabelAdapter(this, viewModel.myLabels.value!!)
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
}
