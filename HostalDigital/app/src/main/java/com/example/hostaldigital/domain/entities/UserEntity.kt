package com.example.hostaldigital.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entidad usuarios
@Entity(tableName = "users")
data class UserEntity(

    //Id del usuario, sera la primary key y se le añadira automaticamente a cada usuario registrado
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    val id: Int = 0,

    //Nombre del usuario
    @ColumnInfo("username")
    val username: String,

    // Contraseña del usuario
    @ColumnInfo(name = "password")
    val password: String,


    @ColumnInfo("isOwner")
    val isOwner: Boolean = false
)