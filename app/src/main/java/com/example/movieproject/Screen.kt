package com.example.movieproject

sealed class Screen (val route: String){
    object MainScreen : Screen("main screen")
    object DetailScreen : Screen("detail screen")
}