package com.nexters.fullstack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nexters.fullstack.helper.PermissionHelper
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
        private set

    abstract val layoutRes: Int

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