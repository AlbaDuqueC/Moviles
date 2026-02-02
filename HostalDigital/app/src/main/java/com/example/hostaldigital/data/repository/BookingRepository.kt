package com.example.hostaldigital.data.repository


import com.example.hostaldigital.domain.dao.BookingDao
import com.example.hostaldigital.domain.dao.RoomDao
import com.example.hostaldigital.domain.entities.BookingEntity
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.ui.model.Booking
import com.example.hostaldigital.ui.model.BookingStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookingRepository(
    private val bookingDao: BookingDao,
    private val roomDao: RoomDao
) {

    fun getBookingsByUser(userId: Int): Flow<List<Booking>> {
        return bookingDao.getBookingsByUser(userId).map { bookings ->
            bookings.map { it.toDomain() }
        }
    }

    fun getActiveBookings(): Flow<List<Booking>> {
        return bookingDao.getActiveBookings().map { bookings ->
            bookings.map { it.toDomain() }
        }
    }

    fun getAllBookings(): Flow<List<Booking>> {
        return bookingDao.getAllBookings().map { bookings ->
            bookings.map { it.toDomain() }
        }
    }

    fun getBookingsByRoom(roomId: Int): Flow<List<Booking>> {
        return bookingDao.getBookingsByRoom(roomId).map { bookings ->
            bookings.map { it.toDomain() }
        }
    }

    suspend fun createBooking(
        userId: Int,
        roomId: Int,
        checkInDate: Long,
        checkOutDate: Long,
        totalPrice: Double
    ): Result<Long> {
        return try {
            // Verificar si la habitación está disponible
            val room = roomDao.getRoomById(roomId)
            if (room == null || !room.isAvailable) {
                return Result.failure(Exception("La habitación no está disponible"))
            }

            // Verificar si hay una reserva activa para esta habitación
            val activeBooking = bookingDao.getActiveBookingForRoom(roomId, System.currentTimeMillis())
            if (activeBooking != null) {
                return Result.failure(Exception("La habitación ya tiene una reserva activa"))
            }

            // Crear la reserva
            val bookingId = bookingDao.insert(
                BookingEntity(
                    userId = userId,
                    roomId = roomId,
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    totalPrice = totalPrice,
                    status = "ACTIVE"
                )
            )

            // Marcar la habitación como no disponible
            roomDao.updateRoomAvailability(roomId, false)

            Result.success(bookingId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun cancelBooking(bookingId: Int): Result<Unit> {
        return try {
            val booking = bookingDao.getBookingById(bookingId)
            if (booking == null) {
                return Result.failure(Exception("Reserva no encontrada"))
            }

            if (booking.status == "COMPLETED") {
                return Result.failure(Exception("No se puede cancelar una reserva finalizada"))
            }

            if (booking.status == "CANCELLED") {
                return Result.failure(Exception("La reserva ya está cancelada"))
            }

            // Cancelar la reserva
            bookingDao.cancelBooking(bookingId)

            // Marcar la habitación como disponible
            roomDao.updateRoomAvailability(booking.roomId, true)

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun completeBooking(bookingId: Int, makeRoomAvailable: Boolean = true): Result<Unit> {
        return try {
            val booking = bookingDao.getBookingById(bookingId)
            if (booking == null) {
                return Result.failure(Exception("Reserva no encontrada"))
            }

            // Completar la reserva
            bookingDao.completeBooking(bookingId)

            // Marcar la habitación como disponible si se indica
            if (makeRoomAvailable) {
                roomDao.updateRoomAvailability(booking.roomId, true)
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun BookingWithDetails.toDomain() = Booking(
        id = booking.id,
        userId = booking.userId,
        roomId = booking.roomId,
        roomNumber = room.roomNumber,
        roomType = room.type,
        userName = user.username,
        checkInDate = booking.checkInDate,
        checkOutDate = booking.checkOutDate,
        totalPrice = booking.totalPrice,
        status = when (booking.status) {
            "ACTIVE" -> BookingStatus.ACTIVE
            "CANCELLED" -> BookingStatus.CANCELLED
            "COMPLETED" -> BookingStatus.COMPLETED
            else -> BookingStatus.ACTIVE
        },
        createdAt = booking.createdAt
    )
}