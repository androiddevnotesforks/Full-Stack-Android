package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import com.bumptech.glide.Glide
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when{
            intent?.action == Intent.ACTION_SEND
                && intent.type?.startsWith(Constants.IMAGE_PREFIX) == true -> {
                    setImage(intent)
                }
        }
    }

    private fun setImage(intent : Intent){
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { uri : Uri ->
            Glide.with(this)
                .load(uri)
                .into(binding.ivScreenshot)
        }
    }
}
