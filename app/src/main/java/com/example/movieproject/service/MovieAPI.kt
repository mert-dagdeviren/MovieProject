package com.example.movieproject.service

import com.example.movieproject.model.MovieData
import com.example.movieproject.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieAPI {
    @GET("shows")
    suspend fun getShows(): List<MovieResponse>
    @GET("movies/{id}")
    suspend fun getMovieDetail(@Path("id") movieId: Int): MovieData
}