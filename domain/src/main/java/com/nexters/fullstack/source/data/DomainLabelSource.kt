package com.nexters.fullstack.source.data

data class DomainLabelSource(
    var type: Int,
    val color: String,
    val name: String,
    var isSelected: Boolean = false
)
