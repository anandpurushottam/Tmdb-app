package com.example.moviesapp.di

import com.example.moviesapp.ui.list.ListFragment
import com.example.moviesapp.ui.list.MovieViewModel
import dagger.Component


@Component(modules = [CoreDataModule::class, DataModule::class, MovieModule::class])
interface MovieComponent {
    fun movieVM(): MovieViewModel
    fun inject(listFragment: ListFragment)
}
