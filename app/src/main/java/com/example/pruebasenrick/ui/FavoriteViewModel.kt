package com.example.pruebasenrick.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebasenrick.data.local.PersonajesLocal
import com.example.pruebasenrick.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.pruebasenrick.data.repository.IFavoriteRepository




class FavoriteViewModel(
    private val favoriteRepository: IFavoriteRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<PersonajesLocal>>(emptyList())
    val favorites: StateFlow<List<PersonajesLocal>> = _favorites

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadFavorites() {
        viewModelScope.launch {
            _isLoading.value = true
            _favorites.value = favoriteRepository.getFavorites()
            _isLoading.value = false
        }
    }

    fun addToFavorites(personaje: PersonajesLocal) {
        viewModelScope.launch {
            favoriteRepository.addFavorite(personaje)
            loadFavorites() // recargar lista
        }
    }

    fun removeFromFavorites(personaje: PersonajesLocal) {
        viewModelScope.launch {
            favoriteRepository.removeFavorite(personaje)
            loadFavorites() // recargar lista
        }
    }
}
