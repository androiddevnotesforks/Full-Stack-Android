package com.nexters.fullstack.ext

import android.util.TypedValue
import com.nexters.fullstack.App

val Int.toPx: Int
    get() {
        val metrics = App().applicationContext.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
    }