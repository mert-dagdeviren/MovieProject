package com.example.movieproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.model.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel() : ViewModel() {
    private val repository = MovieRepository(RetrofitInstance.api)
    private val dataStoreManager = DataStoreManager.getInstance()

    private val _movies = MutableStateFlow<List<MovieData>>(emptyList())
    val movies: StateFlow<List<MovieData>> = _movies

    private val _favorites = MutableStateFlow<List<MovieData>>(emptyList())
    val favorites: StateFlow<List<MovieData>> = _favorites

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadMovies()
        loadFavorites()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _movies.value = repository.getMovies()
            } catch (e: Exception) {
                _error.value =  "Filmler yüklenirken hata oluştu: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            dataStoreManager.getFavorites().collect { favoriteList ->
                _favorites.value = favoriteList
            }
        }
    }

    fun toggleFavorite(movie: MovieData) {
        viewModelScope.launch {
            try {
                dataStoreManager.toggleFavorite(movie)
            } catch (e: Exception) {
                _error.value = "Favori işlemi başarısız: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    fun clearAllFavorites() {
        viewModelScope.launch {
            try {
                dataStoreManager.clearAllFavorites()
            } catch (e: Exception) {
                _error.value = "Favoriler temizlenirken hata oluştu: ${e.message}"
            }
        }
    }

}