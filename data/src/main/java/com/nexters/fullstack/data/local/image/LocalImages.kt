package com.nexters.fullstack.data.local.image

interface LocalImages {
    fun fetch(filterValue: String): ArrayList<String>

    fun getSize(): Int
}