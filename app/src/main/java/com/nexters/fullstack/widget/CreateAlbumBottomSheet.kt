package com.nexters.fullstack.widget

import android.content.Context
import com.nexters.fullstack.databinding.DialogCreateLabelBinding

class CreateAlbumBottomSheet(context: Context) :
    BaseBottomSheet<DialogCreateLabelBinding>(
        true,
        viewBinding = DialogCreateLabelBinding::inflate,
        context = context
    ) {

    override fun initView() {
        binding.close.setOnClickListener { dismiss() }
    }
}