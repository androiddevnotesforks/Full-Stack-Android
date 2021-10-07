package com.nexters.fullstack.domain.entity

import java.io.Serializable

data class FileImageEntity(
    val id: String,
    val originUrl: String
) : Serializable
