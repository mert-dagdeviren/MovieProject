package com.example.movieproject.service

import com.example.movieproject.model.MovieResponse
import retrofit2.http.GET

interface MovieAPI {
    @GET("shows")
    suspend fun getShows(): List<MovieResponse>

}