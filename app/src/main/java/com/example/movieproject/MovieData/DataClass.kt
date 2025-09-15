package com.example.movieproject.MovieData

data class MovieData(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterResId: Int,
)
data class MovieTab(
    val title: String,
    val icon: Int,
    val route: String
)