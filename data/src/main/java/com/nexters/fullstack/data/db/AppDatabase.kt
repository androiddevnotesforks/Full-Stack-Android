package com.nexters.fullstack.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nexters.fullstack.data.db.converter.LocalImageDataConverter
import com.nexters.fullstack.data.db.converter.UserLabelConverter
import com.nexters.fullstack.data.db.dao.ImageDAO
import com.nexters.fullstack.data.db.dao.LabelDAO
import com.nexters.fullstack.data.db.entity.UserLabel
import com.nexters.fullstack.data.db.entity.UserLabelingImage

@Database(
    entities = [UserLabel::class, UserLabelingImage::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(UserLabelConverter::class, LocalImageDataConverter::class)
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

object TableName {
    const val LABEL = "userLabel"

    const val IMAGE = "userImage"

    const val FAVORITE = "favorite"
}