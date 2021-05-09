package com.example.moviesapp.domain

import com.example.moviesapp.data.Result
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies
import javax.inject.Inject

class FetchMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun loadNowPlaying(page:Int): Result<Movies> = repository.loadNowPlaying(page)
    suspend fun loadMovieCast(movieId:Int): Result<MovieCast> = repository.loadMovieCast(movieId)
}