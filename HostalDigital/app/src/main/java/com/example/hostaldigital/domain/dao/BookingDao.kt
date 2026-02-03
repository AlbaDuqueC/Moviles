package com.example.hostaldigital.domain.dao

import androidx.room.*
import com.example.hostaldigital.domain.entities.BookingEntity
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.ui.model.BookingStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    // Da el listado de reservas por usuario ordenadas por fecha de creación
    @Transaction
    @Query("SELECT * FROM bookings WHERE userId = :userId ORDER BY createdAt DESC")
    fun getBookingsByUser(userId: Int): Flow<List<BookingWithDetails>>

    // Obtiene las reservas activas
    @Transaction
    @Query("SELECT * FROM bookings WHERE status = 'ACTIVE' ORDER BY checkInDate")
    fun getActiveBookings(): Flow<List<BookingWithDetails>>

    // Obtiene todas las reservas ordenadas por creación
    @Transaction
    @Query("SELECT * FROM bookings ORDER BY createdAt DESC")
    fun getAllBookings(): Flow<List<BookingWithDetails>>

    // Obtiene todas las reservas con sus relaciones (Room y User)
    @Transaction
    @Query("SELECT * FROM bookings")
    fun getAllBookingsWithDetails(): Flow<List<BookingWithDetails>>

    // Obtiene reservas de una habitación específica
    @Transaction
    @Query("SELECT * FROM bookings WHERE roomId = :roomId ")
    fun getRoomBookingsWithDetails(roomId: Int): Flow<List<BookingWithDetails>>

    // Obtiene reservas de una habitación ordenadas por creación
    @Transaction
    @Query("SELECT * FROM bookings WHERE roomId = :roomId ORDER BY createdAt DESC")
    fun getBookingsByRoom(roomId: Int): Flow<List<BookingWithDetails>>

    // Obtiene detalles de reserva por usuario
    @Transaction
    @Query("SELECT * FROM bookings WHERE userId = :userId ")
    fun getUserBookingsWithDetails(userId: Int): Flow<List<BookingWithDetails>>

    // Busca una reserva por su ID
    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    suspend fun getBookingById(bookingId: Int): BookingEntity?

    // Inserta una entidad de reserva
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(booking: BookingEntity): Long

    // Actualiza una reserva completa
    @Update
    suspend fun update(booking: BookingEntity)

    // Actualiza solo el estado de la reserva (usado por el Repository)
    @Query("UPDATE bookings SET status = :status WHERE id = :bookingId")
    suspend fun updateBookingStatus(bookingId: Int, status: String)

    // Accesos directos para cancelar o completar
    @Query("UPDATE bookings SET status = 'CANCELLED' WHERE id = :bookingId")
    suspend fun cancelBooking(bookingId: Int)

    @Query("UPDATE bookings SET status = 'COMPLETED' WHERE id = :bookingId")
    suspend fun completeBooking(bookingId: Int)

    // Verifica si hay una reserva activa para una habitación
    @Query("SELECT * FROM bookings WHERE roomId = :roomId AND status = 'ACTIVE' AND checkOutDate > :currentTime LIMIT 1")
    suspend fun getActiveBookingForRoom(roomId: Int, currentTime: Long): BookingEntity?

    // Obtiene reservas filtradas por estado (Enum BookingStatus)
    @Transaction
    @Query("""
        SELECT * FROM bookings 
        WHERE status = :status
        ORDER BY checkInDate ASC
    """)
    fun getBookingsByStatus(status: BookingStatus): Flow<List<BookingWithDetails>>
}