package com.nexters.fullstack.db.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.nexters.fullstack.source.LocalImageData

data class UserLabelingImage(
    @PrimaryKey
    @ColumnInfo(name = "label")
    val label: UserLabel,

    @ColumnInfo(name = "images")
    val images: List<LocalImageData>
)
