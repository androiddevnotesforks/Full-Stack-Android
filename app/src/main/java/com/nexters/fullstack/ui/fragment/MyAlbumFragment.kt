package com.nexters.fullstack.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.nexters.fullstack.base.BaseFragment
import com.nexters.fullstack.databinding.FragmentMyalbumBinding
import com.nexters.fullstack.R
import com.nexters.fullstack.widget.CreateAlbumBottomSheet
import com.nexters.fullstack.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyAlbumFragment : BaseFragment<FragmentMyalbumBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.fragment_myalbum
    override val viewModel: MainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addLabel.setOnClickListener {
//            CreateAlbumBottomSheet(requireContext()).show()
            //todo LabelCreate Activity
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