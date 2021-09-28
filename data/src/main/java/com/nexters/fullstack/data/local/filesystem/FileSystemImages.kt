package com.nexters.fullstack.data.local.filesystem

interface FileSystemImages {
    fun fetch(filterValue: String): ArrayList<String>

    fun getSize(): Int
}