package com.nexters.fullstack.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun FragmentManager.removeFragment(vararg fragments: Fragment) {
    fragments.forEach { fragment ->
        this.beginTransaction().remove(fragment)
    }
}