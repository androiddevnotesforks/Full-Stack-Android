package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelOutappBinding
import com.nexters.fullstack.viewmodel.LabelOutAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class LabelOutAppFragment : BaseFragment<FragmentLabelOutappBinding, LabelOutAppViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_outapp
    override val viewModel: LabelOutAppViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    companion object {
        private var instance: Fragment? = null
        fun getInstance(): Fragment =
            instance ?: LabelOutAppFragment().also { instance = it }
    }

    private fun initView(){
        setScreenshot()
    }

    private fun setScreenshot(){
        val uri = Uri.parse(requireArguments().getString("screenshot"))
        Glide.with(this)
            .load(uri)
            .into(binding.ivScreenshot)
    }
}