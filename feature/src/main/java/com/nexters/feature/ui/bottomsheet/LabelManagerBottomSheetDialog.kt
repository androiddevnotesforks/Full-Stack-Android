package com.nexters.feature.ui.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.feature.R
import com.nexters.feature.databinding.LayoutLabelManagerBottomSheetBinding

//todo BottomSheetAdapter
class LabelManagerBottomSheetDialog(private val bottomSheetAdapter: Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: LayoutLabelManagerBottomSheetBinding

    override fun getTheme(): Int {
        return R.style.iOSBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_label_manager_bottom_sheet, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)
}