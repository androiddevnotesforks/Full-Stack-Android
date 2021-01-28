package com.nexters.data.source

import java.util.*

data class LocalLabelData(val id: Long = UUID.randomUUID().timestamp(), val originUrl: String)
