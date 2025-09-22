package com.example.movieproject.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.model.MovieData
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val repository = MovieRepository(RetrofitInstance.api)

    private var _movie by mutableStateOf<MovieData?>(null)
    val movie: MovieData? get() = _movie

    private var _isLoading by mutableStateOf(false)
    val isLoading: Boolean get() = _isLoading

    private var _error by mutableStateOf<String?>(null)
    val error: String? get() = _error

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _isLoading = true
            _error = null

            try {
                val allMovies = repository.getMovies()
                _movie = allMovies.firstOrNull { it.id == movieId }

                if (_movie == null) {
                    _error = "Film bulunamadı (ID: $movieId)"
                }

            } catch (e: Exception) {
                _error = when (e) {
                    is retrofit2.HttpException -> {
                        when (e.code()) {
                            404 -> "Film bulunamadı"
                            500 -> "Sunucu hatası"
                            else -> "Bağlantı hatası: ${e.code()}"
                        }
                    }

                    is java.net.UnknownHostException -> "İnternet bağlantısını kontrol edin"
                    else -> "Beklenmeyen hata: ${e.message}"
                }
                _movie = null
            } finally {
                _isLoading = false
            }
        }
    }


}