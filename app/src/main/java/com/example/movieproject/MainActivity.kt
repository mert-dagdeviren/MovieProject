package com.example.movieproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MovieHomeScreen()
            }
        }
    }
}
data class MovieTab(
    val title: String,
    val icon: Int,
    val route: String
)

@Composable
fun MovieHomeScreen() {
    val tabs = listOf(
        MovieTab(stringResource(R.string.favorites), R.drawable.ic_favorite, "favorites"),
        MovieTab(stringResource(R.string.movies), R.drawable.ic_movies, "movies"),
        MovieTab(stringResource(R.string.visibility), R.drawable.ic_visibility, "visibility")
    )

    var selectedTab by remember { mutableStateOf(tabs[0]) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            MovieBottomBar(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                navController = navController
            )
        }
    ) { innerPadding ->

        Navigation(navController = navController)
    }
}

@Composable
fun MovieBottomBar(
    tabs: List<MovieTab>,
    selectedTab: MovieTab,
    onTabSelected: (MovieTab) -> Unit,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF000000))
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
fun FavoritesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Favoriler Ekranı", color = Color.Magenta)
    }
}

@Composable
fun MoviesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Filmler Ekranı", color = Color.Green)
    }
}

@Composable
fun VisibilityScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("İzlenilenler Ekranı", color = Color.Blue)
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "favorites") {
        composable("favorites") {
            FavoritesScreen()
        }
        composable("movies") {
            MoviesScreen()
        }
        composable("visibility") {
            VisibilityScreen()
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
    val textColor = if (isSelected) (Color(0xFFEC9214)) else Color.White

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier.size(48.dp),


            )
        Text(
            text = title,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


