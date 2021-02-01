package com.nexters.fullstack.source

import java.time.LocalDate

data class LocalImageData(
    val id: Int,
    val count: Int,
    val createTime: LocalDate,
    val lastSearchTime: LocalDate,
    val thumbNail: String,
    val originUrl: String
)
