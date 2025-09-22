
package com.example.movieproject.ui.viewmodel

import com.example.movieproject.model.MovieData
import com.example.movieproject.service.MovieAPI

class MovieRepository(private val api: MovieAPI) {

    suspend fun getMovies(): List<MovieData> {
        val shows = api.getShows()
        return shows.map { movie ->
            MovieData(
                id = movie.id,
                title = movie.name,
                rating = (movie.rating?.average ?: 0.0),
                posterUrl = movie.image?.original ?: "",
                year = movie.premiered?.take(4)?.toIntOrNull() ?: 0,
                genre = movie.genres.joinToString(", "),
            )
        }
    }

    suspend fun getMovieById(id: Int): MovieData {
        val movie = api.getMovieDetail(id)
        return MovieData(
            id = movie.id,
            title = movie.name,
            rating = (movie.rating?.average ?: 0.0),
            posterUrl = movie.image?.original ?: "",
            year = movie.premiered?.take(4)?.toIntOrNull() ?: 0,
            genre = movie.genres.joinToString(", "),
        )
    }
}