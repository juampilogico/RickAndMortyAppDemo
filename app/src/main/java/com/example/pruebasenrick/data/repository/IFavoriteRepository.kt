package com.example.pruebasenrick.data.repository

import com.example.pruebasenrick.data.local.PersonajesLocal

interface IFavoriteRepository {
    suspend fun getFavorites(): List<PersonajesLocal>
    suspend fun addFavorite(personaje: PersonajesLocal)
    suspend fun removeFavorite(personaje: PersonajesLocal)
}
