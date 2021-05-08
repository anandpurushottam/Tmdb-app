package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieCast(
    @SerializedName("cast") val cast: List<Cast>,
    @SerializedName("id") val id: Int
)

data class Cast(
    @SerializedName("cast_id")
    val cast_id: Int,
    @SerializedName("character")
    val character: String,
    @SerializedName("credit_id")
    val credit_id: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("profile_path")
    val profile_path: String
)