package com.example.movieproject.navigation

sealed class ScreenRoutes(val route: String) {
    data object MoviesScreen : ScreenRoutes("Movies Screen")
    data object VisibilitiesScreen : ScreenRoutes("Visibilities Screen"){
        fun createRoute(movieId: Int) = "movieDetail/$movieId"
    }
    data object FavoritesScreen : ScreenRoutes("Favorites Screen")
    object MovieDetailScreen : ScreenRoutes("movieDetail/{movieId}") {
        fun createRoute(movieId: Int) = "movieDetail/$movieId"
    }
}