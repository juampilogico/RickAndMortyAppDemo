package com.example.pruebasenrick.data.repository

import android.content.Context
import com.example.pruebasenrick.data.local.LocalDatabase
import com.example.pruebasenrick.data.local.PersonajesLocal
import com.google.firebase.auth.FirebaseAuth

class FavoriteRepository(context: Context) : IFavoriteRepository {

    private val dao = LocalDatabase.getInstance(context).personajesDao()
    private val uid: String
        get() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    override suspend fun getFavorites(): List<PersonajesLocal> {
        return dao.getAll(uid)
    }

    override suspend fun addFavorite(personaje: PersonajesLocal) {
        dao.insert(personaje.copy(userUid = uid))
    }

    override suspend fun removeFavorite(personaje: PersonajesLocal) {
        dao.delete(personaje.copy(userUid = uid))
    }
}
