package com.nexters.fullstack.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nexters.fullstack.data.db.TableName
import com.nexters.fullstack.data.source.LocalImageData
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TableName.IMAGE)
data class UserLabelingImage(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "label")
    val label: List<UserLabel>,

    @ColumnInfo(name = "image")
    val image: LocalImageData
) : Parcelable
