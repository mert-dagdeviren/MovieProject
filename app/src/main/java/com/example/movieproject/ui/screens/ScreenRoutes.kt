package com.example.movieproject.ui.screens

sealed class ScreenRoutes(val route: String) {
    data object MoviesScreen : ScreenRoutes("Movies Screen")
    data object VisibilitiesScreen : ScreenRoutes("Visibilities Screen")
    data object FavoritesScreen : ScreenRoutes("Favorites Screen")
    data object MovieDetailScreen : ScreenRoutes("movieDetail/{movieId}")
}