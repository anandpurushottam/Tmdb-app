package com.example.moviesapp.domain

import com.example.moviesapp.data.model.Movie
import javax.inject.Inject
import com.example.moviesapp.data.Result

class FetchMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend fun loadNowPlaying(): Result<List<Movie>> = repository.loadNowPlaying(1)
}