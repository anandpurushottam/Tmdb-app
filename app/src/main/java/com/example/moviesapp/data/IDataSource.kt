package com.example.moviesapp.data

import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.MovieDetail


interface IDataSource {
    suspend fun loadNowPlaying(page: Int): Result<List<Movie>>
    suspend fun loadPopular(page: Int): Result<List<Movie>>
    suspend fun loadMovieDetail(movieId:Int): Result<MovieDetail>
    suspend fun loadMovieCast(movieId:Int): Result<MovieCast>
}