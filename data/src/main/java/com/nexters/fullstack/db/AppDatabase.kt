package com.nexters.fullstack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nexters.fullstack.db.converter.LocalImageDataConverter
import com.nexters.fullstack.db.converter.UserLabelConverter
import com.nexters.fullstack.db.dao.FavoriteDAO
import com.nexters.fullstack.db.dao.ImageDAO
import com.nexters.fullstack.db.dao.LabelDAO
import com.nexters.fullstack.db.entity.UserLabel
import com.nexters.fullstack.db.entity.UserLabelingImage

@Database(
    entities = [UserLabel::class, UserLabelingImage::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(UserLabelConverter::class, LocalImageDataConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun labelDAO(): LabelDAO

    abstract fun imageDAO(): ImageDAO

    abstract fun favoriteDAO(): FavoriteDAO

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