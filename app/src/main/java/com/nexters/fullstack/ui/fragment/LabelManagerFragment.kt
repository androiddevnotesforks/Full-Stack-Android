package com.nexters.fullstack.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.nexters.fullstack.BR
import com.nexters.fullstack.BusImpl
import com.nexters.fullstack.Constants.LABEL_BUNDLE_KEY
import com.nexters.fullstack.MainActivity
import com.nexters.fullstack.R
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentLabelManagerBinding
import com.nexters.fullstack.source.LabellingState
import com.nexters.fullstack.source.LocalFile
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.nexters.fullstack.ui.activity.LabelingActivity
import com.nexters.fullstack.ui.adapter.MainStackAdapter
import com.yuyakaido.android.cardstackview.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LabelManagerFragment : BaseFragment<FragmentLabelManagerBinding, MainViewModel>(),
    CardStackListener {
    override val layoutRes: Int = R.layout.fragment_label_manager
    override val viewModel: MainViewModel by viewModel()

    private val stackAdapter: MainStackAdapter by lazy {
        MainStackAdapter()
    }

    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }


    init {
        disposable.add(BusImpl.publish()
            .subscribeOn(Schedulers.computation())
            .onErrorReturn {
                0
            }
            .subscribe { result ->
                if (result == Activity.RESULT_CANCELED) {
                    binding.cardStackView.rewind()
                }
            })

    }

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

    }

    override fun onCardAppeared(view: View?, position: Int) {
        stackAdapter.isSwipe = true
    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    private fun startActivityWithData() {
        val intent = Intent(this@LabelManagerFragment.context, LabelingActivity::class.java)
        intent.putExtras(bundleOf(LABEL_BUNDLE_KEY to stackAdapter.getItem(manager.topPosition)))
        startActivityForResult(intent, 2000)
    }
}