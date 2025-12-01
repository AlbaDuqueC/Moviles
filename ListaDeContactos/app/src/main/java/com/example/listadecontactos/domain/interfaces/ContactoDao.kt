package com.example.listadecontactos.domain.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.listadecontactos.data.entities.Contacto

@Dao
interface ContactoDao {

    @Query("SELECT * FROM contacto_entity")
    suspend fun getAllContacto(): MutableList<Contacto>

    @Insert fun addContacto(contacto: Contacto): Long

    @Query("SELECT * FROM contacto_entity WHERE id LIKE :id")
    suspend fun getContactoById(id: Long) : Contacto

    @Update
    suspend fun  updateContacto (task: Contacto): Int

    @Delete
    suspend fun deleteContacto(task: Contacto): Int

}