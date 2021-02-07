package com.tsdev.feature

import android.content.Context

interface LocalImages {
    fun fetch(filterValue: String): ArrayList<String>
}