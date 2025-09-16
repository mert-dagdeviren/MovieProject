package com.example.movieproject.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.movieproject.R
import com.example.movieproject.model.MovieData

class MovieDetailViewModel : ViewModel() {

    private var _movieId by mutableStateOf<Int?>(null)

    private val movies = listOf(
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

    val movie: MovieData?
        get() = _movieId?.let { id -> movies.find { it.id == id } }

    fun setMovieId(movieId: Int) {
        _movieId = movieId
    }
}