package com.example.movieproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.movieproject.model.MovieTab
import com.example.movieproject.navigation.Navigation
import com.example.movieproject.navigation.ScreenRoutes
import com.example.movieproject.ui.screens.MovieBottomBar
import com.example.movieproject.ui.viewmodel.DataStoreManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataStoreManager.getInstance().init(this)

        setContent {
            MaterialTheme {
                MovieApp()
            }
        }
    }
}
@Composable
fun MovieApp() {

    val context = LocalContext.current

    var selectedTab by remember {
        mutableStateOf(
            MovieTab(
                title = ScreenRoutes.MoviesScreen.route,
                icon = R.drawable.ic_movies,
                route = ScreenRoutes.MoviesScreen.route
            )
        )
    }

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            MovieBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Navigation(navController = navController)
        }
    }
}