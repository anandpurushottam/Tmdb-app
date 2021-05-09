package com.example.moviesapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp.data.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Movie>)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

}