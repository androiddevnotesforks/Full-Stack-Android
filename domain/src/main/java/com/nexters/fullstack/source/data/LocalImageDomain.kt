package com.nexters.fullstack.source.data

import java.io.Serializable
import java.util.*

data class LocalImageDomain(
    val id: Long = UUID.randomUUID().mostSignificantBits,
    val originUrl: String
): Serializable
