package com.nexters.fullstack.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nexters.fullstack.db.entity.UserFavoriteImage
import io.reactivex.Completable

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun like(value: UserFavoriteImage): Completable

    @Delete
    fun unlink(value: UserFavoriteImage): Completable

}