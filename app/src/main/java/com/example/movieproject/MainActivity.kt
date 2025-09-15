package com.example.movieproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieproject.model.MovieTab
import com.example.movieproject.ui.screens.FavoritesScreen
import com.example.movieproject.ui.screens.MovieDetailScreen
import com.example.movieproject.ui.screens.MoviesScreen
import com.example.movieproject.ui.screens.ScreenRoutes
import com.example.movieproject.ui.screens.VisibilitiesScreen
import com.example.movieproject.ui.theme.CustomOrange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp() {
    var selectedTab by remember {
        mutableStateOf(
            MovieTab(
                title = ScreenRoutes.FavoritesScreen.route,
                icon = R.drawable.ic_favorite,
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

@Composable
fun MovieBottomBar(
    selectedTab: MovieTab?,
    onTabSelected: (MovieTab) -> Unit,
    navController: NavHostController
) {
    val tabs = listOf(
        MovieTab(
            stringResource(R.string.favorites),
            R.drawable.ic_favorite,
            ScreenRoutes.FavoritesScreen.route
        ),
        MovieTab(
            stringResource(R.string.movies),
            R.drawable.ic_movies,
            ScreenRoutes.MoviesScreen.route
        ),
        MovieTab(
            stringResource(R.string.visibility),
            R.drawable.ic_visibility,
            ScreenRoutes.VisibilitiesScreen.route
        )
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabs.forEach { tab ->
            MovieBottomBarItem(
                title = tab.title,
                painter = painterResource(id = tab.icon),
                isSelected = (tab == selectedTab),
                onClick = {
                    onTabSelected(tab)
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

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
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt()
            MovieDetailScreen(movieId = movieId)
        }
    }
}

@Composable
fun MovieBottomBarItem(
    title: String,
    painter: Painter,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) CustomOrange else Color.White

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


