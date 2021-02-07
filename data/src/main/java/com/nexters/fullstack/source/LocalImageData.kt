package com.nexters.fullstack.source

import java.util.*


data class LocalImageData(val id: Long = UUID.randomUUID().mostSignificantBits, val originUrl: String)
