package com.example.movieproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieproject.R
import com.example.movieproject.model.MovieData
import com.example.movieproject.ui.theme.CustomGray

@Composable
fun MoviesScreen(navController: NavController) {
    val movies = listOf(
        MovieData(
            id = 1,
            titleResId = R.string.movie_ForestGump,
            rating = 6.2,
            posterResId = R.drawable.ic_forest_gump,
            year = 1994,
            genreResId = R.string.genre_forrestGump,
            actorsResId = R.string.actors_forrestGump
        ),
        MovieData(
            id = 2,
            titleResId = R.string.movie_HarryPotter,
            rating = 8.0,
            posterResId = R.drawable.ic_harry_potter,
            year = 2001,
            genreResId = R.string.genre_harryPotter,
            actorsResId = R.string.actors_harryPotter
        ),
        MovieData(
            id = 3,
            titleResId = R.string.movie_arog,
            rating = 7.6,
            posterResId = R.drawable.ic_arog,
            year = 2008,
            genreResId = R.string.genre_arog,
            actorsResId = R.string.actors_arog
        ),
        MovieData(
            id = 4,
            titleResId = R.string.movie_gora,
            rating = 7.4,
            posterResId = R.drawable.ic_gora,
            year = 2004,
            genreResId = R.string.genre_gora,
            actorsResId = R.string.actors_gora
        ),
        MovieData(
            id = 5,
            titleResId = R.string.movie_toyStory,
            rating = 4.9,
            posterResId = R.drawable.ic_toy_story,
            year = 1995,
            genreResId = R.string.genre_toyStory,
            actorsResId = R.string.actors_toyStory
        ),
        MovieData(
            id = 6,
            titleResId = R.string.movie_martian,
            rating = 9.2,
            posterResId = R.drawable.ic_the_martian,
            year = 2015,
            genreResId = R.string.genre_theMartian,
            actorsResId = R.string.actors_theMartian
        ),
        MovieData(
            id = 7,
            titleResId = R.string.movie_lifeOfPi,
            rating = 8.7,
            posterResId = R.drawable.ic_pi,
            year = 2012,
            genreResId = R.string.genre_theLifePi,
            actorsResId = R.string.actors_theLifePi
        ),
        MovieData(
            id = 8,
            titleResId = R.string.movie_arifV216,
            rating = 6.4,
            posterResId = R.drawable.ic_arif216,
            year = 2018,
            genreResId = R.string.genre_arifV216,
            actorsResId = R.string.actors_arifV216
        ),
        MovieData(
            id = 9,
            titleResId = R.string.movie_interstellar,
            rating = 8.7,
            posterResId = R.drawable.ic_interstellar,
            year = 2014,
            genreResId = R.string.genre_interstellar,
            actorsResId = R.string.actors_interstellar
        ),
        MovieData(
            id = 10,
            titleResId = R.string.movie_superman,
            rating = 8.2,
            posterResId = R.drawable.ic_superman,
            year = 2025,
            genreResId = R.string.genre_superman,
            actorsResId = R.string.actors_superman
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(15.dp)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onMovieClick = { clickedMovie ->
                    navController.navigate(
                        ScreenRoutes.MovieDetailScreen.createRoute(movie.id)
                    )
                }
            )
        }
    }
}
@Composable
fun MovieItem(movie: MovieData, onMovieClick: (MovieData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp, RoundedCornerShape(8.dp))
            .clickable {
                onMovieClick(movie)
            },
        colors = CardDefaults.cardColors(CustomGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = movie.posterResId),
                    contentDescription = stringResource(movie.titleResId),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(
                    text = stringResource(movie.titleResId),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
            FavoriteIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )

            Text(
                text = stringResource(R.string.star) + " " + movie.rating,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)

            )
        }
    }
}
@Composable
fun FavoriteIcon(modifier: Modifier) {
    var isFavorite by remember { mutableStateOf(false) }

    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = if (isFavorite) stringResource(R.string.addFav) else stringResource(R.string.removeFav),
        tint = if (isFavorite) Color.Red else Color.White,
        modifier = Modifier
            .size(28.dp)
            .clickable {
                isFavorite = !isFavorite
            }
    )
}


