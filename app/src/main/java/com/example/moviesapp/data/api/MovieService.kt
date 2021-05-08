package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.data.model.MovieCast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): Response<Movies>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(@Path("movie_id") movie_id: Int): Response<MovieCast>

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
    }
}