package com.nexters.fullstack.widget

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.fullstack.BR
import com.nexters.fullstack.databinding.BsLayoutColorSelectBinding
import com.nexters.fullstack.viewmodel.LabelingViewModel

class LabelColorBottomSheet(private val vm: LabelingViewModel) :
    BottomSheetDialogFragment() {

    lateinit var binding: BsLayoutColorSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BsLayoutColorSelectBinding.inflate(layoutInflater)

        binding.lifecycleOwner = this@LabelColorBottomSheet.viewLifecycleOwner
        binding.setVariable(BR.vm, vm)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInitView()
    }

    private fun onInitView() {

        binding.palletLayout.setOnLabelClickListener = {
            it.copy(backgroundColor = "#2D2015")
        }
    }
}