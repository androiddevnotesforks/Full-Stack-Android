package com.nexters.fullstack.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.fullstack.db.TableName

@Entity(tableName = TableName.FAVORITE)
data class UserFavoriteImage(
    @PrimaryKey
    @ColumnInfo(name = "fileName")
    val fileName: String,

    @ColumnInfo(name = "fileUrl")
    val url: String
)
