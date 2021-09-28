package com.nexters.fullstack.domain.entity

data class DomainLabelSource(
    var type: Int = 0,
    val color: String,
    val name: String,
    var isSelected: Boolean = false
)