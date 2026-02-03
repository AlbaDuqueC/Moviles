package com.example.hostaldigital.ui.model

import com.example.hostaldigital.domain.entities.RoomEntity

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
/**
 * Función de extensión para convertir la Entidad de la base de datos
 * al Modelo de la UI. Pon esto aquí para tener el código organizado.
 */
fun RoomEntity.toUiModel(): Room {
    return Room(
        id = this.id,
        roomNumber = this.roomNumber,
        type = this.type,
        price = this.price,
        capacity = this.capacity,
        description = this.description,
        isAvailable = this.isAvailable
    )
}