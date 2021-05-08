package com.example.moviesapp.di

import com.example.moviesapp.data.api.MovieService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideMovieService(client: OkHttpClient, gson: Gson): MovieService {
        return Retrofit.Builder()
            .baseUrl(MovieService.ENDPOINT)
            .callFactory(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MovieService::class.java)
    }

}
