package com.example.movieproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.model.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository(RetrofitInstance.api)

    private val _movies = MutableStateFlow<List<MovieData>>(emptyList())
    val movies: StateFlow<List<MovieData>> = _movies

    init {
        loadMovies()
    }
    fun loadMovies() {
        viewModelScope.launch {
            try {
                _movies.value = repository.getMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}