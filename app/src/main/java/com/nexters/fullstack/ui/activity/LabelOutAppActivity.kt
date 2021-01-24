package com.nexters.fullstack.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding
import com.nexters.fullstack.ext.loadFragment
import com.nexters.fullstack.ui.fragment.LabelOutAppFragment
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LabelOutAppActivity : BaseActivity<ActivityLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.activity_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()
    private val labelOutAppFragment = LabelOutAppFragment.getInstance()
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when{
            intent?.action == Intent.ACTION_SEND
                && intent.type?.startsWith(Constants.IMAGE_PREFIX) == true -> {
                    handleImage(intent)
                }
        }
        initView()
    }

    private fun initView() {
        supportFragmentManager.loadFragment(
            binding.containerBottom.id,
            labelOutAppFragment
        )
        activeFragment = labelOutAppFragment
        supportFragmentManager.beginTransaction().show(activeFragment).commit()
    }

    private fun handleImage(intent : Intent){
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { uri : Uri ->
            labelOutAppFragment.apply {
                arguments = Bundle().apply {
                    putString(Constants.SS_BUNDLE_KEY, uri.toString())
                }
            }
        }
    }

}
