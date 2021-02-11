package com.nexters.fullstack.source

data class LabelSource(
    var type: Int,
    val color: String,
    val name: String,
    var isSelected: Boolean = false
) {
    companion object {
        const val DEFAULT = 0
        const val RECOMMEND = 1
        const val SELECTED = 2
        const val LIST = 3
    }
}