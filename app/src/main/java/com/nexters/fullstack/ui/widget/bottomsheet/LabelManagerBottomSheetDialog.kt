package com.nexters.fullstack.ui.widget.bottomsheet

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nexters.fullstack.BR
import com.nexters.fullstack.Constants
import com.nexters.fullstack.R
import com.nexters.fullstack.databinding.LayoutLabelManagerBottomSheetBinding
import com.nexters.fullstack.data.db.entity.UserLabel
import com.nexters.fullstack.presentaion.source.dialog.DeleteDialogItem
import com.nexters.fullstack.ui.activity.CreateLabelActivity
import com.nexters.fullstack.ui.adapter.listener.BottomSheetClickListener
import com.nexters.fullstack.ui.adapter.source.ItemType
import com.nexters.fullstack.viewmodel.BottomSheetViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

class LabelManagerBottomSheetDialog(
    private val data: UserLabel
) :
    BottomSheetDialogFragment(), KoinComponent, BottomSheetClickListener {

    private lateinit var binding: LayoutLabelManagerBottomSheetBinding

    private val bottomSheetViewModel by viewModel<BottomSheetViewModel>()

    private val dialog by lazy {
        AlertDialog.Builder(this.requireContext(), R.style.DialogTheme)
    }

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
        binding.setVariable(BR.vm, bottomSheetViewModel)
        binding.event = this@LabelManagerBottomSheetDialog
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBottomSheet.setHasFixedSize(true)
        setObserver()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    companion object {
        fun getInstance(
            data: UserLabel
        ): LabelManagerBottomSheetDialog {
            return LabelManagerBottomSheetDialog(data).apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.BOTTOM_SHEET_KEY, data)
                }
            }
        }
    }

    private fun setObserver() {
        with(bottomSheetViewModel.output) {
            getDialogItem().observe(this@LabelManagerBottomSheetDialog.viewLifecycleOwner) {
                dialog
                    .setTitle(it.title)
                    .setMessage(it.message)
                    .setNegativeButton(it.cancel) { dialogInterface, i ->

                        Log.e("negative", "click")
                    }
                    .setPositiveButton(it.positive) { _, _ ->
                        Log.e("positive", "click")
                    }
                    .create()
                    .show()
            }
        }
    }

    /**
     * BottomSheet Click Event
     **/
    override fun onClick(type: ItemType) {
        when (type) {
            ItemType.DELETE -> {
                bottomSheetViewModel.input.setDialogItem(
                    DeleteDialogItem(
                        requireContext().getString(R.string.title),
                        requireContext().getString(R.string.message),
                        requireContext().getString(R.string.cancel),
                        requireContext().getString(R.string.delete)
                    )
                )
                dismiss()
            }
            ItemType.UPDATE -> {
                dismiss()
                val intent = Intent(requireContext(), CreateLabelActivity::class.java)
                intent.putExtra(Constants.LABEL_MODIFY_KEY, data)
                requireContext().startActivity(intent)
            }
        }
    }
}