package com.example.pruebasenrick.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.pruebasenrick.data.Character


@Dao

interface IPersonajesDao {

    @Query("SELECT * FROM personajes WHERE userUid = :uid")
    suspend fun getAll(uid: String): List<PersonajesLocal>


    @Query("SELECT * FROM personajes WHERE id = :id LIMIT 1")
    suspend fun findById(id : Int): PersonajesLocal


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg personajes : PersonajesLocal)

    @Delete
    suspend fun delete(personajes : PersonajesLocal)



}