package com.nexters.fullstack.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.loadFragment(fragmentId: Int, vararg fragments: Fragment) {
    fragments.forEachIndexed { index, fragment ->
        if (index == fragments.size) {
            this.beginTransaction().add(fragmentId, fragment, fragment.tag).commit()
        } else {
            this.beginTransaction().add(fragmentId, fragment, fragment.tag).hide(fragment).commit()
        }
    }
}

fun FragmentManager.removeFragment(vararg fragments: Fragment) {
    fragments.forEach { fragment ->
        this.beginTransaction().remove(fragment)
    }
}