package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentPictureSearchBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PictureSearchFragment : BaseFragment<FragmentPictureSearchBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_picture_search
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private var instance: PictureSearchFragment? = null
        fun getInstance(): PictureSearchFragment {
            if (instance == null) {
                instance = PictureSearchFragment()
            }
            return PictureSearchFragment()
        }
    }
}