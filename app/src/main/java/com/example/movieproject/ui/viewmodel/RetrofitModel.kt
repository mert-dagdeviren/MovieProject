package com.example.movieproject.ui.viewmodel

import com.example.movieproject.service.MovieAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val api: MovieAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }
}
