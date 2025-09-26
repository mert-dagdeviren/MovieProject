package com.example.movieproject.model


data class MovieResponse(
    val id: Int,
    val name: String,
    val language: String?,
    val genres: List<String>,
    val premiered: String?,
    val rating: Rating?,
    val image: Image?
)