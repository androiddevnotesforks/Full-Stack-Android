package com.nexters.fullstack.widget

import android.content.Context
import android.util.Log
import com.nexters.fullstack.databinding.DialogCreateLabelBinding

class CreateAlbumBottomSheet(context: Context) :
    BaseBottomSheet<DialogCreateLabelBinding>(
        viewBinding = DialogCreateLabelBinding::inflate,
        context = context
    ) {

    override fun initView() {

        binding.close.setOnClickListener {
            Log.e("error", "click")
            dismiss()
        }
    }
}