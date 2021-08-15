package com.nexters.fullstack.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nexters.fullstack.db.entity.UserLabel
import io.reactivex.Completable

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun like(value: UserLabel): Completable

    @Delete
    fun unlink(value: UserLabel): Completable

}