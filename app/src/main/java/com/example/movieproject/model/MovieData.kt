package com.example.movieproject.model

import com.example.movieproject.service.MovieAPI
import com.example.movieproject.ui.viewmodel.RetrofitInstance

data class MovieData(
    val id: Int,
    val title: String,
    val rating: Double,
    val posterUrl: String,
    val year: Int,
    val genre: String,
    // val actors: String (actors yok şimdilik)
)

data class MovieResponse(
    val id: Int,
    val name: String,
    val language: String?,
    val genres: List<String>,
    val premiered: String?,
    val rating: Rating?,
    val image: Image?
)

data class Rating(val average: Double?)
data class Image(val medium: String?, val original: String?)

class MovieRepository(api: MovieAPI) {
    suspend fun getMovies(): List<MovieData> {
        val shows = RetrofitInstance.api.getShows()
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
}