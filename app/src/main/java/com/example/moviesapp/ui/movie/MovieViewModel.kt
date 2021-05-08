package com.example.moviesapp.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.FetchMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.moviesapp.data.Result
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.util.PaginationScrollListener
import kotlinx.coroutines.Job

class MovieViewModel @Inject constructor(private val useCase: FetchMovieUseCase) : ViewModel() {

    val movies = MutableLiveData<List<Movie>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    var loading = false
    var currentPage = 0
    var totalPage = 1

    init {
        loadMovie()
    }

    fun loadMovie() {
        loadNowPlaying(++currentPage)
    }

    private fun loadNowPlaying(page: Int) = viewModelScope.launch {
        progress.value = true
        error.value = false
        loading = true
        val result: Result<Movies> = withContext(Dispatchers.IO) { useCase.loadNowPlaying(page) }
        progress.value = false
        when (result) {
            is Result.Success -> handleSuccess(result.data)
            is Result.Error -> handleError()
        }
    }

    private fun handleError() {
        loading = false
        currentPage--
        error.value=true
    }

    private fun handleSuccess(data: Movies) {
        currentPage = data.page
        totalPage = data.total_pages
        loading = false
        movies.value=data.movies
    }


}