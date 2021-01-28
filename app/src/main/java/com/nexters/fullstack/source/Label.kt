package com.nexters.fullstack.source

data class Label(
    val type: Int, val name : String
){
    companion object{
        const val DEFAULT = 0
        const val RECOMMEND = 1
    }
}
