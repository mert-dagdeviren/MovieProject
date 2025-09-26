package com.example.movieproject.navigation

import MovieDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieproject.ui.screens.FavoritesScreen
import com.example.movieproject.ui.screens.MoviesScreen
import com.example.movieproject.ui.screens.VisibilitiesScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.MoviesScreen.route
    ) {
        composable(ScreenRoutes.FavoritesScreen.route) {
            FavoritesScreen(navController)
        }

        composable(ScreenRoutes.MoviesScreen.route) {
            MoviesScreen(navController)
        }

        composable(ScreenRoutes.VisibilitiesScreen.route) {
            VisibilitiesScreen(navController)
        }

        composable(
            route = ScreenRoutes.MovieDetailScreen.route
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()

            if (movieId != null) {
                MovieDetailScreen(movieId = movieId)
            }
        }
    }
}