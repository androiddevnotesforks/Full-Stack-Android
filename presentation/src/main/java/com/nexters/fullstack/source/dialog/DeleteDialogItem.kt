package com.nexters.fullstack.source.dialog

data class DeleteDialogItem(
    val title: String, val message: String, val cancel: String, val positive: String,
    val cancelListener: (() -> Unit)? = null, val positiveListener: (() -> Unit)? = null
)
