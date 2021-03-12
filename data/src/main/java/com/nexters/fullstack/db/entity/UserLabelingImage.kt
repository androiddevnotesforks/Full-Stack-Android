package com.nexters.fullstack.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.fullstack.db.TableName
import com.nexters.fullstack.source.LocalImageData
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TableName.IMAGE)
data class UserLabelingImage(
    @PrimaryKey
    @ColumnInfo(name = "label")
    val label: List<UserLabel>,

    @ColumnInfo(name = "image")
    val image: LocalImageData
) : Parcelable
