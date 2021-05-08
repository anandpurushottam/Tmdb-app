package com.example.moviesapp.data

import com.example.moviesapp.data.api.MovieService
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.MovieDetail
import java.io.IOException

class RemoteDataSource(private val apiService: MovieService) : IDataSource {
    override suspend fun loadNowPlaying(page: Int): Result<List<Movie>> {
        val response = apiService.getNowPlaying(1)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body.movies)
            }
        }
        return Result.Error(IOException("Getting ${response.code()} ${response.message()}"))

    }

    override suspend fun loadPopular(page: Int): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun loadMovieDetail(movieId: Int): Result<MovieDetail> {
        TODO("Not yet implemented")
    }

    override suspend fun loadMovieCast(movieId: Int): Result<MovieCast> {
        TODO("Not yet implemented")
    }
}