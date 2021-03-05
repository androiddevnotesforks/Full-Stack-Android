package com.nexters.fullstack.ui.widget.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.LayoutLabelManagerBottomSheetBinding
import com.nexters.fullstack.viewmodel.BottomSheetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

//todo BottomSheetAdapter
class LabelManagerBottomSheetDialog(private val bottomSheetAdapter: Unit) :
    BottomSheetDialogFragment(), KoinComponent {

    private lateinit var binding: LayoutLabelManagerBottomSheetBinding

    private val bottomSheetViewModel by viewModel<BottomSheetViewModel>()

    override fun getTheme(): Int {
        return R.style.iOSBottomSheetDialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_label_manager_bottom_sheet,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.vm, bottomSheetViewModel)
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)
}