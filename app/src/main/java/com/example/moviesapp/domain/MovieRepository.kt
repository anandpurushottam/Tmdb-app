package com.example.moviesapp.domain

import android.util.Log
import com.example.moviesapp.data.LocalDataSource
import com.example.moviesapp.data.RemoteDataSource
import com.example.moviesapp.data.Result
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun loadNowPlaying(page: Int): Result<Movies> {
        return when (val result = remoteDataSource.loadNowPlaying(page)) {
            is Result.Success -> result
            is Result.Error -> result
        }
    }
    suspend fun loadMovieCast(movieId: Int): Result<MovieCast> {
        return when (val result = remoteDataSource.loadMovieCast(movieId)) {
            is Result.Success -> result
            is Result.Error -> {
                result
            }
        }
    }
}