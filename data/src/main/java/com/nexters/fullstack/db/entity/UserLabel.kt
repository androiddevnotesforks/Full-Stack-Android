package com.nexters.fullstack.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.fullstack.db.TableName

@Entity(tableName = TableName.LABEL)
data class UserLabel(
    @PrimaryKey
    @ColumnInfo(name = "color")
    val color: String,

    @ColumnInfo(name = "text")
    val text: String
)
