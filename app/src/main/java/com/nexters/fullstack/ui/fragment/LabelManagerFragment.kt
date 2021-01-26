package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.nexters.fullstack.BR
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelManagerBinding
import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.ui.activity.LabelingActivity
import com.nexters.fullstack.ui.adapter.MainStackAdapter
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class LabelManagerFragment : BaseFragment<FragmentLabelManagerBinding, MainViewModel>(),
    CardStackListener {
    override val layoutRes: Int = R.layout.fragment_label_manager
    override val viewModel: MainViewModel by viewModel()

    private val stackAdapter: MainStackAdapter by lazy {
        MainStackAdapter()
    }

    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
        setOnClickListener()
        observer()

        bind {
            setVariable(BR.vm, viewModel)
        }
    }

    private fun setOnClickListener() {
        binding.labeledButton.setOnClickListener {
            viewModel.setButtonState(LabellingState.Approve)
        }
        binding.skipButton.setOnClickListener {
            viewModel.setButtonState(LabellingState.Rejected)
        }
    }

    private fun viewInit() {
        binding.stackView.adapter = stackAdapter
        binding.stackView.layoutManager = manager
    }


    private fun observer() {
        viewModel.labellingState.observe(
            this@LabelManagerFragment.viewLifecycleOwner,
            Observer { state ->
                if (viewModel.isLabellingStart(state)) {
                    startActivity(
                        Intent(
                            this@LabelManagerFragment.context,
                            LabelingActivity::class.java
                        )
                    )
                } else {
                    //todo 맨 뒤로 이동.
                }
            })
    }

    companion object {
        private var instance: LabelManagerFragment? = null
        fun getInstance(): LabelManagerFragment {
            if (instance == null) {
                instance = LabelManagerFragment()
            }
            return LabelManagerFragment()
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }
}