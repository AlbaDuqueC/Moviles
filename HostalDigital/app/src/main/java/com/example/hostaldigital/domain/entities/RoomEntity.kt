package com.example.hostaldigital.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rooms")
data class RoomEntity(

    // Id de la habitacion
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    // Numero de la habitacion
    @ColumnInfo("roomNumber")
    val roomNumber: String,

    // Tipo de habitacion
    @ColumnInfo("type")
    val type: String, // Single, Double, Suite

    // Precio de la habitacion
    @ColumnInfo("price")
    val price: Double,

    // Capacidad de la habitacion, numero de personas que caben
    @ColumnInfo("capacity")
    val capacity: Int,

    // Descripcion de la habitacion
    @ColumnInfo("description")
    val description: String,

    // Boolean que define si esta ocupada la habitacion o no
    @ColumnInfo("isAvailable")
    val isAvailable: Boolean = true
)