package com.example.moviesapp.domain

import com.example.moviesapp.data.LocalDataSource
import com.example.moviesapp.data.RemoteDataSource
import com.example.moviesapp.data.Result
import com.example.moviesapp.data.model.Movie

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun loadNowPlaying(page: Int): Result<List<Movie>> {
        return when (val result = remoteDataSource.loadNowPlaying(page)) {
            is Result.Success -> remoteDataSource.loadNowPlaying(page)
            is Result.Error -> result
        }
    }

}