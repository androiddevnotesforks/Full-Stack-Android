package com.nexters.fullstack.widget

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.BsLayoutColorSelectBinding

class LabelColorBottomSheet(context: Context) :
    BottomSheetDialog(context, R.style.BottomSheetDefaultStyle) {

    lateinit var binding: BsLayoutColorSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BsLayoutColorSelectBinding.inflate(layoutInflater)

        setContentView(binding.root)
        initView()
    }

    override fun onStart() {
        super.onStart()

        if (behavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun initView() {

    }
}