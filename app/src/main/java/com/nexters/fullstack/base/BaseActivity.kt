package com.nexters.fullstack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.nexters.fullstack.helper.PermissionHelper
import org.koin.android.ext.android.inject

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    protected lateinit var binding: VB
        private set

    abstract val layoutRes: Int
    abstract val viewModel : VM

    internal val permissionHelper by inject<PermissionHelper>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        if (!permissionHelper.isAcceptPermission()) {
            ActivityCompat.requestPermissions(
                this,
                permissionHelper.requestPermissions.toTypedArray(),
                permissionHelper.requestCode
            )
        }
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    fun bind(body: VB.() -> Unit) {
        binding.run(body)
    }
}