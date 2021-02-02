package com.nexters.data.source

import java.util.*


data class LocalLabelData(val id: Long = UUID.randomUUID().mostSignificantBits, val originUrl: String)
