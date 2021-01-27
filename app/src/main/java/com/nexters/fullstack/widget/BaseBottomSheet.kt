package com.nexters.fullstack.widget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.ActivityLabelOutappBinding.inflate
import com.nexters.fullstack.databinding.DialogCreateLabelBinding

abstract class BaseBottomSheet<VB: ViewBinding>(
    private var isExpanded: Boolean = false,
    private val viewBinding: (LayoutInflater) -> VB,
    context: Context
) : BottomSheetDialog(context, R.style.BottomSheetDefaultStyle) {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding(layoutInflater)

        setContentView(viewBinding(layoutInflater).root)

        initView()
    }

    override fun onStart() {
        super.onStart()

        if (behavior.state != BottomSheetBehavior.STATE_EXPANDED && isExpanded) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    abstract fun initView()
}