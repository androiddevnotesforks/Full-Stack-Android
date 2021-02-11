package com.nexters.fullstack.db.dao

import androidx.room.*
import com.nexters.fullstack.db.entity.UserLabel
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface LabelDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(label: UserLabel): Completable

    @Update
    fun update(label: UserLabel): Completable

    @Delete
    fun delete(label: UserLabel): Completable

    @Query("select * from userLabel")
    fun load(): Maybe<List<UserLabel>>
}