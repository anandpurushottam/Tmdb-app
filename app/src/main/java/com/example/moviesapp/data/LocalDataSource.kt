package com.example.moviesapp.data

import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.MovieDetail

class LocalDataSource:IDataSource {
    override suspend fun loadNowPlaying(page: Int): Result<List<Movie>> {
        TODO("Not yet implemented")
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