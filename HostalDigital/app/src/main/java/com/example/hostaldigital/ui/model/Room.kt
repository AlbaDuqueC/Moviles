package com.example.hostaldigital.ui.model

// model room
data class Room(

    // Id del room
    val id: Int,

    // numero de la habitacion
    val roomNumber: String,

    // tipo de habitacion
    val type: String,

    // precio de la habitacion
    val price: Double,

    // capacidad de la habitacion, numero de personas
    val capacity: Int,

    // descripcion de la habitacion
    val description: String,

    // si esta ocupada o no
    val isAvailable: Boolean
)