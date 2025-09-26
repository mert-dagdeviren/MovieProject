package com.example.movieproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieproject.model.MovieData
import com.example.movieproject.ui.theme.CustomOrange

@Composable
fun MovieDetailContent(
    movie: MovieData,
    isFavorite: Boolean,
    onFavoriteClick: (MovieData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                shape = RoundedCornerShape(50.dp),
                width = 10.dp,
                color = CustomOrange
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "⭐ ${movie.rating}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = CustomOrange,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "Year: ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Genre: ${movie.genre}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                IconButton(onClick = { onFavoriteClick(movie) }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}
