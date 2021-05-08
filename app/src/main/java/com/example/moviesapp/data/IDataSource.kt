package com.example.moviesapp.data

import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies


interface IDataSource {
    suspend fun loadNowPlaying(page: Int): Result<Movies>
    suspend fun loadMovieCast(movieId:Int): Result<MovieCast>
}