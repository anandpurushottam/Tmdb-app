package com.example.moviesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.domain.FetchMovieUseCase
import com.example.moviesapp.ui.movie.MovieViewModel
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor(private val useCase: FetchMovieUseCase) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}