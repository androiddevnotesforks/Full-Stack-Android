package com.nexters.fullstack.source.bottomsheet

import com.nexters.fullstack.source.local.DomainUserImage

data class BottomSheetItem(
    val type: Int,
    val title: String,
    var onClickListener: ((DomainUserImage) -> Unit)? = null
)