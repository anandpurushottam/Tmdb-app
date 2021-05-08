package com.example.moviesapp.ui.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.domain.FetchMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.moviesapp.data.Result

class MovieViewModel @Inject constructor(private val useCase: FetchMovieUseCase) : ViewModel() {

    val newsHeadLine = MutableLiveData<List<Movie>>()
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()


    fun loadNowPlaying() = viewModelScope.launch {
        progress.value = true
        error.value = false
        val result: Result<List<Movie>> = withContext(Dispatchers.IO) { useCase.loadNowPlaying() }
        progress.value = false
        when (result) {
            is Result.Success -> {
                Log.d("MovieViewModel", "data: " + result.data.size)
                newsHeadLine.postValue(result.data)
            }
            is Result.Error -> {
                error.postValue(true)
                Log.d("MovieViewModel", "Error: " + result.exception)
            }
        }
    }


}