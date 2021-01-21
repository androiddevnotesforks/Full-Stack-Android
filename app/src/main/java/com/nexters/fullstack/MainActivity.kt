package com.nexters.fullstack

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nexters.fullstack.base.BaseActivity
import com.nexters.fullstack.databinding.ActivityMainBinding
import com.nexters.fullstack.ext.loadFragment
import com.nexters.fullstack.ui.fragment.LabelManagerFragment
import com.nexters.fullstack.ui.fragment.MyAlbumFragment
import com.nexters.fullstack.ui.fragment.PictureSearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutRes: Int = R.layout.activity_main

    private val labelManagerFragment = LabelManagerFragment.getInstance()
    private val pictureSearchFragment = PictureSearchFragment.getInstance()
    private val myAlbumFragment = MyAlbumFragment.getInstance()
    private var mainFragment: Fragment = labelManagerFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        bind { }
    }

    private fun initView() {
        supportFragmentManager.loadFragment(
            binding.frameLayout.id,
            pictureSearchFragment,
            myAlbumFragment,
            labelManagerFragment
        )

        activeFragment = mainFragment

        supportFragmentManager.beginTransaction().show(activeFragment).commit()
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            fetchBottomSheet(itemId = item.itemId)
            true
        }
    }

    private fun fetchBottomSheet(itemId: Int) {
        when (itemId) {
            R.id.label -> changeFragment(activeFragment, labelManagerFragment)
            R.id.album -> changeFragment(activeFragment, myAlbumFragment)
            R.id.search -> changeFragment(activeFragment, pictureSearchFragment)
        }
    }

    private fun changeFragment(oldFragment: Fragment, newFragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(oldFragment).show(newFragment).commit()
        activeFragment = newFragment
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == permissionHelper.requestCode) {
            for(permission in permissions) {
                if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "허가가 필요합니다.",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}