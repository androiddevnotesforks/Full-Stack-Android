package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentMyalbumBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.widget.CreateAlbumBottomSheet

class MyAlbumFragment : BaseFragment<FragmentMyalbumBinding>() {
    override val layoutRes: Int = R.layout.fragment_myalbum

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addLabel.setOnClickListener {
            CreateAlbumBottomSheet(requireContext()).show()
        }
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
}