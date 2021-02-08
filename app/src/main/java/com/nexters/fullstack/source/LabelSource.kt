package com.nexters.fullstack.source

data class LabelSource(
    var type: Int,
    val color: String,
    val name: String,
    var isSelected: Boolean = false
) {
    companion object {
        const val SELECTED = 0
        const val LIST = 1
    }
}