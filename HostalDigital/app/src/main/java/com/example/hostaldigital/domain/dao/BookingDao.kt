package com.example.hostaldigital.domain.dao

import androidx.room.*
import com.example.hostaldigital.domain.entities.BookingEntity
import com.example.hostaldigital.domain.entities.BookingWithDetails

import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    // da el listado de habitaciones dependiendo del usuario
    @Transaction
    @Query("SELECT * FROM bookings WHERE userId = :userId ORDER BY createdAt DESC")
    fun getBookingsByUser(userId: Int): Flow<List<BookingWithDetails>>

    // marca todas en active
    @Transaction
    @Query("SELECT * FROM bookings WHERE status = 'ACTIVE' ORDER BY checkInDate")
    fun getActiveBookings(): Flow<List<BookingWithDetails>>


    @Transaction
    @Query("SELECT * FROM bookings ORDER BY createdAt DESC")
    fun getAllBookings(): Flow<List<BookingWithDetails>>

    @Transaction
    @Query("SELECT * FROM bookings WHERE roomId = :roomId ORDER BY createdAt DESC")
    fun getBookingsByRoom(roomId: Int): Flow<List<BookingWithDetails>>

    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    suspend fun getBookingById(bookingId: Int): BookingEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(booking: BookingEntity): Long

    @Update
    suspend fun update(booking: BookingEntity)

    @Query("UPDATE bookings SET status = 'CANCELLED' WHERE id = :bookingId")
    suspend fun cancelBooking(bookingId: Int)

    @Query("UPDATE bookings SET status = 'COMPLETED' WHERE id = :bookingId")
    suspend fun completeBooking(bookingId: Int)

    @Query("SELECT * FROM bookings WHERE roomId = :roomId AND status = 'ACTIVE' AND checkOutDate > :currentTime LIMIT 1")
    suspend fun getActiveBookingForRoom(roomId: Int, currentTime: Long): BookingEntity?
}