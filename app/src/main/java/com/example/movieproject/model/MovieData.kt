package com.example.movieproject.model

data class MovieData(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String,
    val year: Int,
    val genre: String,
    // val actors: String (actors yok şimdilik)
)

