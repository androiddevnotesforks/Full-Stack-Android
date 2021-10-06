package com.nexters.fullstack.data.db.dao

import androidx.room.*
import com.nexters.fullstack.data.db.entity.ImageModel
import com.nexters.fullstack.data.db.entity.ImageWithLabels
import com.nexters.fullstack.data.db.entity.LabelModel
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ImageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(image: ImageModel): Completable

    @Query("select * from userImage")
    fun load(): Maybe<List<ImageWithLabels>>

    @Query("select * from userImage where liked == 1")
    fun loadLikes(): Maybe<List<ImageWithLabels>>

    @Query("select * from userImage where liked == 0")
    fun loadUnLikes(): Maybe<List<ImageWithLabels>>

    @Delete
    fun delete(image: ImageModel): Completable
}