package com.nexters.fullstack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentMyalbumBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.source.ActivityResultData
import com.nexters.fullstack.ui.activity.CreateLabelActivity
import com.nexters.fullstack.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MyAlbumFragment : BaseFragment<FragmentMyalbumBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_myalbum
    override val viewModel: MainViewModel by viewModel()
    private val addLabelButtonSubject = BehaviorSubject.create<Unit>()

    init {
        /**
         * 액티비티 중복 호출 방지.
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
    }

    private fun setOnInitClickListener() {
        binding.addLabel.setOnClickListener {
            addLabelButtonSubject.onNext(Unit)
        }
    }

    private fun onInitView() {
//        binding.rvUserImage.adapter =
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