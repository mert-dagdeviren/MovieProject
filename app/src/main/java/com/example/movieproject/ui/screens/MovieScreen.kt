package com.example.movieproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieproject.R
import com.example.movieproject.model.MovieData
import com.example.movieproject.navigation.ScreenRoutes
import com.example.movieproject.ui.theme.CustomOrange
import com.example.movieproject.ui.viewmodel.MovieViewModel

@Composable
fun MoviesScreen(navController: NavController) {
    val viewModel: MovieViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    color = CustomOrange,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(50.dp)
                )
            }

            error != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = error!!,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    androidx.compose.material3.Button(
                        onClick = { viewModel.clearError() }
                    ) {
                        Text("")
                    }
                }
            }

            movies.isEmpty() -> {
                Text(
                    text = stringResource(R.string.not_found),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(15.dp)
                ) {
                    items(movies) { movie ->
                        MovieItem(
                            movie = movie,
                            isFavorite = favorites.any { it.id == movie.id },
                            onMovieClick = { clickedMovie ->
                                navController.navigate(
                                    ScreenRoutes.MovieDetailScreen.createRoute(clickedMovie.id)
                                )
                            },
                            onFavoriteClick = { clickedMovie ->
                                viewModel.toggleFavorite(clickedMovie)
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun MovieItem(
    movie: MovieData,
    isFavorite: Boolean,
    onMovieClick: (MovieData) -> Unit,
    onFavoriteClick: (MovieData) -> Unit
) {
    var localFavorite by remember { mutableStateOf(isFavorite) }
    Card(
        modifier = Modifier
            .border(
                shape = RoundedCornerShape(50.dp),
                width = 8.dp,
                color = CustomOrange
            )
            .fillMaxWidth()
            .shadow(20.dp, RoundedCornerShape(8.dp))
            .clickable { onMovieClick(movie) },
        colors = CardDefaults.cardColors(Color.Black),
        shape = RoundedCornerShape(50.dp)
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
                        .padding(22.dp)
                )

                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(22.dp)
                        .size(28.dp)
                        .clickable {
                            localFavorite = !localFavorite
                            onFavoriteClick(movie)
                            onFavoriteClick(movie)
                        }
                )
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