package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)

data class Movie(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdrop_path: String = "",
    @SerializedName("genre_ids")
    val genre_ids: List<Int>,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("poster_path")
    val poster_path: String = "",
    @SerializedName("release_date")
    val release_date: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("vote_average")
    val vote_average: Double = 0.0,
    @SerializedName("vote_count")
    val vote_count: Int = 0
)