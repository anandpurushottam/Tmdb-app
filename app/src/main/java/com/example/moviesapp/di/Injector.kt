package com.example.moviesapp.di

import android.content.Context
import androidx.lifecycle.ViewModelStore
import com.example.moviesapp.ui.list.ListFragment


fun inject(fragment: ListFragment, viewModelStore: ViewModelStore, context: Context) {
    DaggerMovieComponent.builder().movieModule(MovieModule(viewModelStore, context)).build()
        .inject(fragment)
}