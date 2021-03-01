package com.nexters.fullstack.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.nexters.feature.util.ColorUtils
import com.nexters.fullstack.BR
import com.nexters.fullstack.BusImpl
import com.nexters.fullstack.Constants
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelSelectBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.mapper.LocalFileMapper
import com.nexters.fullstack.mapper.LocalMainLabelMapper
import com.nexters.fullstack.source.LabelSource
import com.nexters.fullstack.source.LocalFile
import com.nexters.fullstack.source.ViewState
import com.nexters.fullstack.ui.adapter.MyLabelAdapter
import com.nexters.fullstack.ui.decoration.SpaceBetweenRecyclerDecoration
import com.nexters.fullstack.viewmodel.LabelingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LabelSelectFragment : BaseFragment<FragmentLabelSelectBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.fragment_label_select
    override val viewModel: LabelingViewModel by sharedViewModel()
    private val labelAdapter = MyLabelAdapter()

    init {
        disposable.add(
            BusImpl.publish()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    if (result == Activity.RESULT_OK) {
                        labelAdapter.notifyDataSetChanged()
                    } else {
                        labelAdapter.addSelectedItem(result as LabelSource)
                        labelAdapter.notifyDataSetChanged()
                    }
                }, {})
        )

        labelAdapter.callback = {
            updateView(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
        }

        setOnInitView()
        setInitOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        viewModel.output.labels().observe(this.viewLifecycleOwner) {

            val mapper = it.items.map { domainUserLabel ->
                LabelSource(color = domainUserLabel.color ?: "", name = domainUserLabel.text)
            }

            labelAdapter.addItems(mapper)
            labelAdapter.notifyDataSetChanged()
        }
    }

    private fun setOnInitView() {
        binding.rvLabel.adapter = labelAdapter
        binding.rvLabel.addItemDecoration(SpaceBetweenRecyclerDecoration(vertical = 10))
    }

    private fun setInitOnClickListener() {
        with(viewModel.input) {
            binding.tvAddLabel.setOnClickListener {
                clickAppbar(ViewState.Add)
            }
            binding.saveButton.setOnClickListener {
                clickLabelingButton(
                    labelAdapter.selectedLabel,
                    LocalFileMapper.toData(
                        arguments?.getParcelable(Constants.LABEL_BUNDLE_KEY) ?: LocalFile("")
                    )
                )
            }
        }
    }

    private fun updateView(labels: MutableList<LabelSource>) {
        if (labels.isEmpty()) {
            binding.selectLinearLayout.visibility = View.GONE
        } else {
            binding.selectLinearLayout.visibility = View.VISIBLE
            binding.selectLinearLayout.removeAllViews()

            for (i in labels.indices) {
                val layout = LayoutInflater.from(requireContext())
                    .inflate(R.layout.selected_label_item, null)
                val title = layout.findViewById<TextView>(R.id.tv_label)
                val cancelButton = layout.findViewById<ImageView>(R.id.cancel_button)
                cancelButton.setOnClickListener {
                    labels.remove(labels[i])
                    binding.selectLinearLayout.removeViewAt(i)
                    updateView(labels)
                    labelAdapter.notifyDataSetChanged()
                }
                title.text = labels[i].name
                setBackgroundTint(cancelButton, labels[i].color)
                binding.selectLinearLayout.addView(layout, 0)
            }
        }
    }

    private fun setBackgroundTint(view: View, color: String) {
        when (view) {
            is TextView -> {

            }
            is ImageView -> {
                view.setColorFilter(ColorUtils(color, requireContext()).getActive())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        labelAdapter.selectedLabelClear()
    }

    companion object {
        private var instance: LabelSelectFragment? = null

        fun getInstance(localFileData: LocalFile? = null): LabelSelectFragment {
            if (localFileData == null) return LabelSelectFragment()
            return instance ?: LabelSelectFragment().apply {
                arguments =
                    Bundle().apply {
                        putParcelable(Constants.LABEL_BUNDLE_KEY, localFileData)
                    }
            }.also { instance = it }
        }
    }
}