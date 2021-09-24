package com.nexters.fullstack.domain.source.data

import java.io.Serializable

data class LocalImageDomain(
    val id: String,
    val originUrl: String
) : Serializable
