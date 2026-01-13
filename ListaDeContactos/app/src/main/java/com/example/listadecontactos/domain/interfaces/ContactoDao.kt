package com.example.listadecontactos.domain.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.listadecontactos.data.entities.Contacto

@Dao
interface ContactoDao {

    @Query("SELECT * FROM contacto_entity ORDER BY name ASC")
    suspend fun getAllContacto(): MutableList<Contacto>

    @Insert
    suspend fun addContacto(contacto: Contacto): Long

    @Query("SELECT * FROM contacto_entity WHERE id = :id")
    suspend fun getContactoById(id: Int): Contacto?

    @Update
    suspend fun updateContacto(contacto: Contacto): Int

    @Delete
    suspend fun deleteContacto(contacto: Contacto): Int
}