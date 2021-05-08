package com.example.moviesapp.dagger

import com.example.moviesapp.ui.detail.DetailFragment
import com.example.moviesapp.ui.movie.MovieListFragment
import com.example.moviesapp.ui.movie.MovieViewModel
import dagger.Component


@Component(modules = [CoreDataModule::class, DataModule::class, MovieModule::class])
interface MovieComponent {
    fun movieVM(): MovieViewModel
    fun inject(listFragment: MovieListFragment)
    fun inject(detailFragment: DetailFragment)
}
