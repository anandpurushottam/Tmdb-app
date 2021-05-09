package com.example.moviesapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesapp.data.database.Converter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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
@Entity
@Parcelize
data class Movie(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    @TypeConverters(Converter::class)
    val backdrop_path: String? = "",
    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("poster_path")
    @TypeConverters(Converter::class)
    val poster_path: String? = "",
    @SerializedName("release_date")
    val release_date: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("vote_average")
    val vote_average: Double = 0.0,
    @SerializedName("vote_count")
    val vote_count: Int = 0
) : Parcelable