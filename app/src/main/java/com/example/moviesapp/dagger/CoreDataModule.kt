package com.example.moviesapp.dagger

import android.content.Context
import com.example.moviesapp.data.api.ClientAuthInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CoreDataModule {

    @Provides
    fun provideOkHttpClient(interceptor: ClientAuthInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    fun provideClientAuthInterceptor(context: Context): ClientAuthInterceptor =
        ClientAuthInterceptor(context)

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

}
