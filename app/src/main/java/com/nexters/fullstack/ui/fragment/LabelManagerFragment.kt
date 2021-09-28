package com.nexters.fullstack.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.os.bundleOf
import com.nexters.fullstack.util.Constants.LABEL_BUNDLE_KEY
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelManagerBinding
import com.nexters.fullstack.model.ActivityResultData
import com.nexters.fullstack.presentaion.viewmodel.MainViewModel
import com.nexters.fullstack.ui.activity.LabelingActivity
import com.nexters.fullstack.ui.adapter.MainStackAdapter
import com.yuyakaido.android.cardstackview.*
import com.nexters.fullstack.BR
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LabelManagerFragment : BaseFragment<FragmentLabelManagerBinding, MainViewModel>(),
    CardStackListener {
    override val layoutRes: Int = R.layout.fragment_label_manager
    override val viewModel: MainViewModel by sharedViewModel()

    private val stackAdapter: MainStackAdapter by lazy {
        MainStackAdapter()
    }

    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            setVariable(BR.vm, viewModel)
        }

        onViewInit()
        setObserver()
    }

    private fun onViewInit() {
        with(manager) {
            setStackFrom(StackFrom.None)
            setStackFrom(StackFrom.Right)
            setVisibleCount(4)
            setTranslationInterval(11.0f)
            setScaleInterval(0.9f)
            setSwipeThreshold(0.3f)
            setMaxDegree(20.0f)
            setDirections(Direction.HORIZONTAL)
            setCanScrollHorizontal(true)
            setCanScrollVertical(true)
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }
        binding.cardStackView.adapter = stackAdapter
        binding.cardStackView.layoutManager = manager
    }


    private fun setObserver() {
        with(viewModel.output) {
            startLabelling().observe(this@LabelManagerFragment.viewLifecycleOwner, {
                if (it != null) {
                    startActivityWithData()
                }
            })
        }
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
        //no-op
        Log.e("ratio ->", ratio.toString())

        binding.skipButton.visibility = View.GONE
        binding.labeledButton.visibility = View.GONE

    }

    override fun onCardSwiped(direction: Direction?) {
        // left -> reject
        // right -> approve
        if (direction == Direction.Right && stackAdapter.isSwipe) {
            startActivityWithData()
        }
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {
        binding.skipButton.visibility = View.VISIBLE
        binding.labeledButton.visibility = View.VISIBLE
    }

    override fun onCardAppeared(view: View?, position: Int) {
        binding.skipButton.visibility = View.VISIBLE
        binding.labeledButton.visibility = View.VISIBLE
    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    private fun startActivityWithData() {
        val intent = Intent(this@LabelManagerFragment.context, LabelingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        intent.putExtras(bundleOf(LABEL_BUNDLE_KEY to stackAdapter.getItem(manager.topPosition - 1)))
        startActivityForResult(intent, 2000)
    }

    override fun onActivityResult(activityResultData: ActivityResultData) {
        if (activityResultData.resultCode == Activity.RESULT_CANCELED) {
            binding.cardStackView.rewind()
        }
    }
}