package com.nexters.fullstack.data.db.dao

import androidx.room.*
import com.nexters.fullstack.data.db.entity.UserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface LabelDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(label: UserLabel): Completable

    @Delete
    fun delete(label: UserLabel): Completable

    @Query("select * from userLabel")
    fun load(): Maybe<List<UserLabel>>
}