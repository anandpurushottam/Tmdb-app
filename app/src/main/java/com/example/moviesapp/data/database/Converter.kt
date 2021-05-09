package com.example.moviesapp.data.database

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromNullToString(value: String?): String {
        return value ?: ""
    }
}