package com.example.moviesapp.data

import com.example.moviesapp.data.api.MovieService
import com.example.moviesapp.data.api.safeApiCall
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies
import java.io.IOException

class RemoteDataSource(private val apiService: MovieService) : IDataSource {
    override suspend fun loadNowPlaying(page: Int): Result<Movies> {
        return safeApiCall {
            val response = apiService.getNowPlaying(page)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return@safeApiCall Result.Success(body)
                }
            }
            return@safeApiCall Result.Error(
                IOException("Getting ${response.code()} ${response.message()}")
            )
        }

    }

    override suspend fun loadMovieCast(movieId: Int): Result<MovieCast> {
        TODO("Not yet implemented")
    }
}