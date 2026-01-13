package com.example.listadecontactos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listadecontactos.data.entities.Contacto
import com.example.listadecontactos.domain.interfaces.ContactoDao

@Database(entities = [Contacto::class], version = 1, exportSchema = false)
abstract class ContactoDataBase : RoomDatabase() {
    abstract fun contactoDao(): ContactoDao
}