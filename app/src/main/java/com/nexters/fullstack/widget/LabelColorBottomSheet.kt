package com.nexters.fullstack.widget

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.BsLayoutColorSelectBinding
import com.nexters.fullstack.viewmodel.LabelingViewModel
import com.tsdev.feature.ui.data.PalletItem

class LabelColorBottomSheet(private val vm: LabelingViewModel) :
    BottomSheetDialogFragment() {

    lateinit var binding: BsLayoutColorSelectBinding

    private val _selectedLabels = mutableListOf<PalletItem>()

    val selectedLabels: List<PalletItem>
        get() = _selectedLabels

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
        binding.tvDoneButton.background =
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.bg_labeling_done
            )


        binding.palletLayout.setOnLabelClickListener = { item ->
            if (_selectedLabels.contains(item)) {
                _selectedLabels.remove(item)
            } else {
                _selectedLabels.add(item)
            }

            if (_selectedLabels.isEmpty()) {
                binding.tvDoneButton.background =
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_labeling_done
                    )

            } else {

            }
        }
    }
}