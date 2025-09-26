import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieproject.R
import com.example.movieproject.ui.screens.ErrorScreen
import com.example.movieproject.ui.screens.LoadingScreen
import com.example.movieproject.ui.screens.MovieDetailContent
import com.example.movieproject.ui.viewmodel.MovieDetailViewModel
import com.example.movieproject.ui.viewmodel.MovieViewModel

@Composable
fun MovieDetailScreen(movieId: Int?) {

    val viewModel: MovieDetailViewModel = viewModel()
    val movieViewModel: MovieViewModel = viewModel()

    if (movieId == null) {
        ErrorScreen(stringResource(R.string.invalid_movie))
        return
    }

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetail(movieId)
    }

    val favorites by movieViewModel.favorites.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewModel.isLoading -> {
                LoadingScreen()
            }

            viewModel.error != null -> {
                ErrorScreen(
                    error = viewModel.error!!,
                    onRetry = { viewModel.loadMovieDetail(movieId) }
                )
            }

            viewModel.movie != null -> {
                val movie = viewModel.movie!!
                val isFavorite = favorites.any { it.id == movie.id }

                MovieDetailContent(
                    movie = movie,
                    isFavorite = isFavorite,
                    onFavoriteClick = { movieViewModel.toggleFavorite(movie) }
                )
            }

            else -> {
                ErrorScreen(stringResource(R.string.not_found))
            }
        }
    }
}