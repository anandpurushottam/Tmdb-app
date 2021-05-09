package com.example.moviesapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.Result
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.model.MovieCast
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.domain.FetchMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val useCase: FetchMovieUseCase) : ViewModel() {

    private val movies = MutableLiveData<List<Movie>>()
    private val movieCast = MutableLiveData<MovieCast>()
    private val error = MutableLiveData<Boolean>()
    private val progress = MutableLiveData<Boolean>()

    val hasError: LiveData<Boolean> = error
    val showLoader: LiveData<Boolean> = progress
    val result: LiveData<List<Movie>> = movies
    val cast: LiveData<MovieCast> = movieCast


    var loadingNextPage = false
        private set
    var currentPage = 0
        private set
    var totalPage = 1
        private set


    init {
        loadMovie()
    }

    fun loadMovie() {
        loadNowPlaying(++currentPage)
    }

    private fun loadNowPlaying(page: Int) = viewModelScope.launch {
        progress.value = page == 1 //show progress only for first load
        error.value = false
        loadingNextPage = true
        val result: Result<Movies> = withContext(Dispatchers.IO) { useCase.loadNowPlaying(page) }
        when (result) {
            is Result.Success -> handleSuccess(result.data)
            is Result.Error -> handleError()
        }
    }
     fun loadMovieCast(movieId: Int) = viewModelScope.launch {
         movieCast.value= MovieCast(Collections.emptyList(),0)
        val result: Result<MovieCast> = withContext(Dispatchers.IO) { useCase.loadMovieCast(movieId) }
        when (result) {
            is Result.Success -> movieCast.value=result.data
            is Result.Error -> handleError()
        }
    }

    private fun handleError() {
        loadingNextPage = false
        currentPage--
        error.value = true
        progress.value = false

    }

    private fun handleSuccess(data: Movies) {
        currentPage = data.page
        totalPage = data.total_pages
        loadingNextPage = false
        movies.value = data.movies
        progress.value = false
    }


}