package com.nexters.fullstack.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Update
import io.reactivex.Completable

@Dao
interface ImageDAO {
    fun save(): Completable

    fun load()

    @Delete
    fun delete(): Completable

    @Update
    fun update(): Completable
}