package com.example.moviesapp.data

import com.example.moviesapp.data.model.Movie


interface IMutableDataSource {
    suspend fun removeAll(): Result<Boolean>
    suspend fun insertAll(list:List<Movie>): Result<Boolean>
    suspend fun loadNowPlaying(): Result<List<Movie>>
}