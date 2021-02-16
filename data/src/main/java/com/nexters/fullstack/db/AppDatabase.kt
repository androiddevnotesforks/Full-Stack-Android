package com.nexters.fullstack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nexters.fullstack.db.dao.ImageDAO
import com.nexters.fullstack.db.dao.LabelDAO
import com.nexters.fullstack.db.entity.UserLabel

@Database(entities = [UserLabel::class], version = 1, exportSchema = false)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun labelDAO(): LabelDAO

    abstract fun imageDAO(): ImageDAO

    companion object {
        private const val DB_NAME = "search_history_db"

        fun getInstance(context: Context): AppDatabase {
            var instance: AppDatabase? = null

            return instance ?: Room
                .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
                .also { instance = it }
        }
    }
}