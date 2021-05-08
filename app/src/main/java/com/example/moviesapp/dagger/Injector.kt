package com.example.moviesapp.dagger

import android.content.Context
import androidx.lifecycle.ViewModelStore
import com.example.moviesapp.ui.detail.DetailFragment
import com.example.moviesapp.ui.movie.MovieListFragment


fun inject(fragment: MovieListFragment, viewModelStore: ViewModelStore, context: Context) {
    DaggerMovieComponent.builder().movieModule(MovieModule(viewModelStore, context)).build()
        .inject(fragment)
}
fun inject(fragment: DetailFragment, viewModelStore: ViewModelStore, context: Context) {
    DaggerMovieComponent.builder().movieModule(MovieModule(viewModelStore, context)).build()
        .inject(fragment)
}