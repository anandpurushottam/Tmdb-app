package com.example.moviesapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.moviesapp.MovieViewModelFactory
import com.example.moviesapp.data.LocalDataSource
import com.example.moviesapp.data.RemoteDataSource
import com.example.moviesapp.data.api.MovieService
import com.example.moviesapp.domain.FetchMovieUseCase
import com.example.moviesapp.domain.MovieRepository
import com.example.moviesapp.ui.list.MovieViewModel
import dagger.Module
import dagger.Provides


@Module
class MovieModule(private val viewModelStore: ViewModelStore, private val context: Context) {

    @Provides
    fun provideMovieViewModel(factory: MovieViewModelFactory): MovieViewModel =
        ViewModelProvider(viewModelStore, factory).get(MovieViewModel::class.java)

    @Provides
    fun provideMovieViewModelFactory(useCase: FetchMovieUseCase): MovieViewModelFactory =
        MovieViewModelFactory(useCase)

    @Provides
    fun provideNewsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): MovieRepository = MovieRepository(remoteDataSource, localDataSource)

    @Provides
    fun provideRemoteDataSource(service: MovieService): RemoteDataSource = RemoteDataSource(service)

    @Provides
    fun provideLocalDataSource(): LocalDataSource = LocalDataSource()

    @Provides
    fun provideContext(): Context = context

}