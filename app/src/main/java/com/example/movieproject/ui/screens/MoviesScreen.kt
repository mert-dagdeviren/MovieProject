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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieproject.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.shadow


data class Movie(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterResId: Int,
)

@Composable
fun MoviesScreen() {
    val movies = listOf(
        Movie(
            id = 1,
            title = stringResource(R.string.movie_ForestGump),
            rating = 6.2,
            posterResId = R.drawable.ic_forest_gump,
        ),
        Movie(
            id = 2,
            title = stringResource(R.string.movie_HarryPotter),
            rating = 8.0,
            posterResId = R.drawable.ic_harry_potter,
        ),
        Movie(
            id = 3,
            title = stringResource(R.string.movie_arog),
            rating = 7.6,
            posterResId = R.drawable.ic_arog,
        ),
        Movie(
            id = 4,
            title = stringResource(R.string.movie_gora),
            rating = 7.4,
            posterResId = R.drawable.ic_gora,
        ),
        Movie(
            id = 5,
            title = stringResource(R.string.movie_toyStory),
            rating = 4.9,
            posterResId = R.drawable.ic_toy_story,
        ),
        Movie(
            id = 6,
            title = stringResource(R.string.movie_martian),
            rating = 9.2,
            posterResId = R.drawable.ic_the_martian,
        ),
        Movie(
            id = 7,
            title = stringResource(R.string.movie_lifeOfPi),
            rating = 8.7,
            posterResId = R.drawable.ic_pi,
        ),
        Movie(
            id = 8,
            title = stringResource(R.string.movie_arifV216),
            rating = 6.4,
            posterResId = R.drawable.ic_arif216,
        ),
        Movie(
            id = 9,
            title = stringResource(R.string.movie_interstellar),
            rating = 8.7,
            posterResId = R.drawable.ic_interstellar,
        ),
        Movie(
            id = 10,
            title = stringResource(R.string.movie_superman),
            rating = 8.2,
            posterResId = R.drawable.ic_superman,
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 90.dp
        )
    ) {
        items(movies) { movie ->
            MovieItem(movie)

        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4D524A)),
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
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(
                    text = movie.title,
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


