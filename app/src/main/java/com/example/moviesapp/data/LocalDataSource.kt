package com.example.moviesapp.data

import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies

class LocalDataSource:IDataSource {
    override suspend fun loadNowPlaying(page: Int): Result<Movies> {
        TODO("Not yet implemented")
    }

    override suspend fun loadMovieCast(movieId: Int): Result<MovieCast> {
        TODO("Not yet implemented")
    }
}