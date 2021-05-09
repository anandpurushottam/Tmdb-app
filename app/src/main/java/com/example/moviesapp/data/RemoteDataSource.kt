package com.example.moviesapp.data

import com.example.moviesapp.data.api.MovieService
import com.example.moviesapp.data.api.safeCall
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies
import java.io.IOException

class RemoteDataSource(private val apiService: MovieService) : IDataSource {
    override suspend fun loadNowPlaying(page: Int): Result<Movies> {
        return safeCall {
            val response = apiService.getNowPlaying(page)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@safeCall Result.Success(body)
                }
            }
            return@safeCall Result.Error(
                IOException("Getting ${response.code()} ${response.message()}")
            )
        }

    }

    override suspend fun loadMovieCast(movieId: Int): Result<MovieCast> {
        return safeCall {
            val response = apiService.getMovieCast(movieId)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@safeCall Result.Success(body)
                }
            }
            return@safeCall Result.Error(
                IOException("Getting ${response.code()} ${response.message()}")
            )
        }
    }
}