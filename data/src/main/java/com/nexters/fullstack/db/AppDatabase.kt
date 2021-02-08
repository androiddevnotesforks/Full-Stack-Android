package com.nexters.fullstack.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nexters.fullstack.db.entity.UserLabel

@Database(entities = [UserLabel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
}