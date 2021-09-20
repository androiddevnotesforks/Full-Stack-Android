package com.nexters.fullstack.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nexters.fullstack.data.db.entity.UserLabel

class UserLabelConverter {
    @TypeConverter
    fun jsonToUserLabel(value: String): List<UserLabel>? {
        val userLabelType = object : TypeToken<List<UserLabel>>() {}.type

        return Gson().fromJson<List<UserLabel>>(value, userLabelType)
    }

    @TypeConverter
    fun fromUserLabel(value: List<UserLabel>): String {
        val gson = Gson()

        return gson.toJson(value)
    }
}