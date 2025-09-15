package com.example.movieproject.model

data class MovieData(
    val id: Int,
    val titleResId: Int,
    val rating: Double,
    val posterResId: Int,
    val year: Int,
    val genreResId: Int,
    val actorsResId: Int
)
