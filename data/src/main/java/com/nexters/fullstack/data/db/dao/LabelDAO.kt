package com.nexters.fullstack.data.db.dao

import androidx.room.*
import com.nexters.fullstack.data.db.entity.ImageWithLabels
import com.nexters.fullstack.data.db.entity.LabelModel
import com.nexters.fullstack.data.db.entity.LabelWithImages
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface LabelDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(label: LabelModel): Completable

    @Delete
    fun delete(label: LabelModel): Completable

    @Query("select * from userLabel")
    fun load(): Maybe<List<LabelModel>>

    @Query("select * from userLabel")
    fun loadWithImages(): Maybe<List<LabelWithImages>>

    @Transaction
    @Query("SELECT * FROM userLabel where labelId IN (:labelIds)")
    fun loadImages(labelIds : List<Long>): List<LabelWithImages>
}