package com.nexters.fullstack.source

import com.nexters.fullstack.source.data.LocalImageDomain

class Screenshot (
    val imageUrl : String,
    val labels : List<Label>?,
    val isFavorite : Boolean,
    var isChecked : Boolean = false
)