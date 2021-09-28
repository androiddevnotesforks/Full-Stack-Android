package com.nexters.fullstack.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nexters.fullstack.data.model.LocalImageData

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