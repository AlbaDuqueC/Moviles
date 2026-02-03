package com.example.hostaldigital.ui.model

// model booking
data class Booking(

    // id del historial
    val id: Int,

    // id del usuario
    val userId: Int,

    // id de la habiracion
    val roomId: Int,

    // numero de la habitacion
    val roomNumber: String,

    // tipo de habitacion
    val roomType: String,

    // nombre del usuario
    val userName: String,

    val checkInDate: Long,
    val checkOutDate: Long,

    // precio total
    val totalPrice: Double,


)

// Estado del hsitorial
enum class BookingStatus {
    ACTIVE,
    CANCELLED,
    COMPLETED
}