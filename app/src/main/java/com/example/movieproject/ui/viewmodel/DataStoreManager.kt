package com.example.movieproject.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.movieproject.model.MovieData
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map



class DataStoreManager() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "movie_preferences")
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: DataStoreManager? = null

        fun getInstance(): DataStoreManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataStoreManager().also {
                    INSTANCE = it
                }
            }
        }
    }

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    private val gson = Gson()
    private val FAVORITES_KEY = stringSetPreferencesKey("favorite_movies")


    fun getFavorites(): Flow<List<MovieData>> = context.dataStore.data.map { preferences ->
        val favoriteJsonSet = preferences[FAVORITES_KEY] ?: emptySet()
        favoriteJsonSet.mapNotNull { jsonString ->
            try {
                gson.fromJson(jsonString, MovieData::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun isFavorite(movieId: Int): Boolean {
        return try {
            val preferences = context.dataStore.data.first()
            val favoriteJsonSet = preferences[FAVORITES_KEY] ?: emptySet()
            favoriteJsonSet.any { jsonString ->
                try {
                    val movie = gson.fromJson(jsonString, MovieData::class.java)
                    movie.id == movieId
                } catch (e: Exception) {
                    false
                }
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun addToFavorites(movie: MovieData) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()
            val movieJson = gson.toJson(movie)


            currentFavorites.removeAll { jsonString ->
                try {
                    val existingMovie = gson.fromJson(jsonString, MovieData::class.java)
                    existingMovie.id == movie.id
                } catch (e: Exception) {
                    false
                }
            }
            currentFavorites.add(movieJson)
            preferences[FAVORITES_KEY] = currentFavorites
        }
    }


    suspend fun removeFromFavorites(movieId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()

            currentFavorites.removeAll { jsonString ->
                try {
                    val movie = gson.fromJson(jsonString, MovieData::class.java)
                    movie.id == movieId
                } catch (e: Exception) {
                    false
                }
            }

            preferences[FAVORITES_KEY] = currentFavorites
        }
    }

    suspend fun toggleFavorite(movie: MovieData) {
        if (isFavorite(movie.id)) {
            removeFromFavorites(movie.id)
        } else {
            addToFavorites(movie)
        }
    }

    suspend fun clearAllFavorites() {
        context.dataStore.edit { preferences ->
            preferences.remove(FAVORITES_KEY)
        }
    }
}