package com.example.pruebasenrick.data.local

import androidx.room.Entity
import com.example.pruebasenrick.data.Location
import com.example.pruebasenrick.data.Origin
import androidx.room.PrimaryKey




@Entity(tableName = "personajes")
data class PersonajesLocal(
    @PrimaryKey val id: Int,
    val userUid: String, // 🔐 Identificador del usuario
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val imageUrl: String = "",
    val episode: List<String>,
    val created: String
)