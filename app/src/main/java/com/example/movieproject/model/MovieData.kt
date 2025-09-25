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

data class MovieResponse(
    val id: Int,
    val name: String,
    val language: String?,
    val genres: List<String>,
    val premiered: String?,
    val rating: Rating?,
    val image: Image?
)

data class MovieTab(
    val title: String,
    val icon: Int,
    val route: String
)

data class Rating(val average: Double?)
data class Image(val medium: String?, val original: String?)

