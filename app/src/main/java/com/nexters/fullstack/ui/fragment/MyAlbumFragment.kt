package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.nexters.fullstack.BR
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentMyalbumBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.source.ActivityResultData
import com.nexters.fullstack.ui.activity.CreateLabelActivity
import com.nexters.fullstack.ui.adapter.LocalImageAdapter
import com.nexters.fullstack.viewmodel.LabelingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MyAlbumFragment : BaseFragment<FragmentMyalbumBinding, LabelingViewModel>() {
    override val layoutRes: Int = R.layout.fragment_myalbum
    override val viewModel: LabelingViewModel by viewModel()
    private val addLabelButtonSubject = BehaviorSubject.create<Unit>()

    private val localImageAdapter by lazy { LocalImageAdapter() }

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
        }
    }

    private fun setOnInitClickListener() {
        binding.addLabel.setOnClickListener {
            addLabelButtonSubject.onNext(Unit)
        }
    }

    private fun onInitView() {

//        binding.rvUserImage.adapter = localImageAdapter
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
}