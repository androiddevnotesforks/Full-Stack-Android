package com.nexters.fullstack.data.db.dao

import androidx.room.*
import com.nexters.fullstack.data.db.entity.UserLabelingImage
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface ImageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(image: UserLabelingImage): Completable

    @Query("select * from userImage")
    fun load(): Maybe<List<UserLabelingImage>>

    @Delete
    fun delete(image: UserLabelingImage): Completable
}