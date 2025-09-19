package com.example.movieproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieproject.model.MovieData
import com.example.movieproject.ui.theme.CustomGray
import com.example.movieproject.ui.viewmodel.MovieViewModel

@Composable
fun MoviesScreen(navController: NavController) {
    val viewModel: MovieViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(15.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie, onMovieClick = { clickedMovie ->
                    navController.navigate(
                        ScreenRoutes.MovieDetailScreen.createRoute(clickedMovie.id)
                    )
                })
        }
    }
}
@Composable
fun MovieItem(
    movie: MovieData, onMovieClick: (MovieData) -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp, RoundedCornerShape(8.dp))
            .clickable { onMovieClick(movie) },
        colors = CardDefaults.cardColors(CustomGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {

                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = "⭐ ${movie.rating}",
                    color = Color.Yellow,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                )

                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp)
                        .clickable { isFavorite = !isFavorite })
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${movie.title} (${movie.year})",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}