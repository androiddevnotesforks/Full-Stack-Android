package com.nexters.fullstack.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nexters.fullstack.source.LocalImageData

class LocalImageDataConverter {
    @TypeConverter
    fun fromLocalImageData(value: LocalImageData): String {
        val gson = Gson()

        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToLocalImageData(value: String): LocalImageData? {
        val type = object : TypeToken<LocalImageData>() {}.type

        return Gson().fromJson<LocalImageData>(value, type)
    }
}