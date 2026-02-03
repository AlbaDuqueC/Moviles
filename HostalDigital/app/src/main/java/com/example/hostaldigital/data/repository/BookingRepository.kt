package com.example.hostaldigital.data.repository

import com.example.hostaldigital.domain.dao.BookingDao
import com.example.hostaldigital.domain.entities.BookingEntity
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.ui.model.BookingStatus
import kotlinx.coroutines.flow.Flow

class BookingRepository(private val bookingDao: BookingDao) {

    // Cambiado de getUserBookings a getBookingsByUser para coincidir con el DAO
    fun getUserBookings(userId: Int): Flow<List<BookingWithDetails>> {
        return bookingDao.getBookingsByUser(userId)
    }

    // Cambiado para coincidir con lo que pide OwnerViewModel
    fun getRoomBookings(roomId: Int): Flow<List<BookingWithDetails>> {
        return bookingDao.getBookingsByRoom(roomId)
    }

    fun getAllBookings(): Flow<List<BookingWithDetails>> {
        return bookingDao.getAllBookings()
    }

    suspend fun createBooking(booking: BookingEntity): Result<Long> {
        return try {
            val id = bookingDao.insert(booking)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun cancelBooking(bookingId: Int): Result<Unit> {
        return try {
            bookingDao.cancelBooking(bookingId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun completeBooking(bookingId: Int): Result<Unit> {
        return try {
            bookingDao.completeBooking(bookingId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getBookingsByStatus(status: BookingStatus): Flow<List<BookingWithDetails>> {
        return bookingDao.getBookingsByStatus(status)
    }

    suspend fun getActiveBookingForRoom(roomId: Int): BookingEntity? {
        return bookingDao.getActiveBookingForRoom(roomId, System.currentTimeMillis())
    }
}