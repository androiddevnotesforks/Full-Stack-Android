package com.nexters.fullstack.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nexters.fullstack.db.entity.UserLabel

class UserLabelConverter {
    @TypeConverter
    fun jsonToUserLabel(value: String): UserLabel? {
        val userLabelType = object : TypeToken<UserLabel>() {}.type

        return Gson().fromJson<UserLabel>(value, userLabelType)
    }

    @TypeConverter
    fun fromUserLabel(value: UserLabel): String {
        val gson = Gson()

        return gson.toJson(value)
    }
}