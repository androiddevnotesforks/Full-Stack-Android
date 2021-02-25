package com.nexters.fullstack.local.image

interface LocalImages {
    fun fetch(filterValue: String): ArrayList<String>
}