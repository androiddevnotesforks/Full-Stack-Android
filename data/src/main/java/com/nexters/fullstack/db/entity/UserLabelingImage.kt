package com.nexters.fullstack.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.fullstack.db.TableName
import com.nexters.fullstack.source.LocalImageData

@Entity(tableName = TableName.IMAGE)
data class UserLabelingImage(
    @PrimaryKey
    @ColumnInfo(name = "label")
    val label: UserLabel,

    @ColumnInfo(name = "image")
    val image: LocalImageData
)
