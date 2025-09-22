package com.example.movieproject.service

import com.example.movieproject.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {
    @GET("shows")
    suspend fun getShows(): List<MovieResponse>
    @GET("shows/{id}")
    suspend fun getMovieDetail(@Path("id") movieId: Int): MovieResponse
}