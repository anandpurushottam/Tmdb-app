package com.example.moviesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapp.data.model.Movie


@Database(entities = [Movie::class], version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private const val DATABASE_NAME = "movie-db"
        fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context, MovieDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}