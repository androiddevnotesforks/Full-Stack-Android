package com.nexters.fullstack.source.bottomsheet

data class BottomSheetItem(val title: String, var onClickListener: ((Any) -> Unit)? = null)