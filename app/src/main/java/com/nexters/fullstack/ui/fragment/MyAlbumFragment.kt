package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentMyalbumBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.mapper.UserLabelingImageMapper
import com.nexters.fullstack.source.ActivityResultData
import com.nexters.fullstack.source.local.DomainUserImage
import com.nexters.fullstack.ui.activity.CreateLabelActivity
import com.nexters.fullstack.ui.adapter.BottomSheetAdapter
import com.nexters.fullstack.ui.adapter.listener.BottomSheetClickListener
import com.nexters.fullstack.ui.adapter.listener.ItemClickListener
import com.nexters.fullstack.ui.adapter.source.ItemType
import com.nexters.fullstack.ui.widget.bottomsheet.LabelManagerBottomSheetDialog
import com.nexters.fullstack.viewmodel.BottomSheetViewModel
import com.nexters.fullstack.viewmodel.LabelingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MyAlbumFragment : BaseFragment<FragmentMyalbumBinding, LabelingViewModel>(),
    ItemClickListener {

    override val layoutRes: Int = R.layout.fragment_myalbum

    override val viewModel: LabelingViewModel by viewModel()

    private val addLabelButtonSubject = BehaviorSubject.create<Unit>()

    init {
        /**
         * 액티비티 중복 호출 방지.
         * todo MainActivity  에서 객체가 한번 다 만들어지니까 또 호출할 땐 중복 호출이 안됌.
         */
        disposable.add(
            addLabelButtonSubject
                .throttleFirst(1000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { startActivity(Intent(requireContext(), CreateLabelActivity::class.java)) },
                    { it.printStackTrace() }
                )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInitView()
        setOnInitClickListener()
        bind {
            setVariable(BR.vm, viewModel)
            setVariable(BR.event, this@MyAlbumFragment)
        }
    }

    private fun setOnInitClickListener() {
        binding.addLabel.setOnClickListener {
            addLabelButtonSubject.onNext(Unit)
        }
    }

    private fun onInitView() {
        //no-op
    }

    companion object {
        private var instance: MyAlbumFragment? = null
        fun getInstance(): MyAlbumFragment {
            if (instance == null) {
                instance = MyAlbumFragment()
            }
            return MyAlbumFragment()
        }
    }

    override fun onActivityResult(activityResultData: ActivityResultData) {
        //no-op
    }

    /**
     * Album Lead More Click Event
     **/
    override fun onClick(item: DomainUserImage) {
        LabelManagerBottomSheetDialog.getInstance(
            UserLabelingImageMapper.toDomain(item)
        ).show(requireActivity().supportFragmentManager, this.tag)
    }
}