package com.example.movieproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.model.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VisibilityViewModel(): ViewModel()  {

    private val _favorites = MutableStateFlow<List<MovieData>>(emptyList())
    val favorites: StateFlow<List<MovieData>> = _favorites
    private val dataStoreManager = DataStoreManager.getInstance()

    private val _visibilities = MutableStateFlow<List<MovieData>>(emptyList())
    val visibilities: StateFlow<List<MovieData>> = _visibilities


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadFavorites()
        loadVisibilities()
    }


    private fun loadVisibilities() {
        viewModelScope.launch {
            dataStoreManager.getVisibilities().collect { visibilityList ->
                _visibilities.value = visibilityList
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
    fun toggleMovie(movie: MovieData) {
        viewModelScope.launch {
            try {
                dataStoreManager.toggleVisibility(movie)
            } catch (e: Exception) {
                _error.value = "Görüntüleme işlemi başarısız: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    fun clearAllVisibilities() {
        viewModelScope.launch {
            try {
                dataStoreManager.clearAllVisibilities()
            } catch (e: Exception) {
                _error.value = "Görüntülenenleri temizlenirken hata oluştu: ${e.message}"
            }
        }
    }
}