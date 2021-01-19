package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentPictureSearchBinding
import com.nexters.fullstack.R

class PictureSearchFragment : BaseFragment<FragmentPictureSearchBinding>() {
    override val layoutRes: Int = R.layout.fragment_picture_search

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