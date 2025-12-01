package com.example.listadecontactos.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacto_entity")
class Contacto (

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val phoneNumber: String,
    val genero: String,

    )