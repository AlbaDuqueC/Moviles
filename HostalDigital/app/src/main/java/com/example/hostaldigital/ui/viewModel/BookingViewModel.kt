package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.BookingRepository
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.ui.model.Booking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _userBookings = MutableStateFlow<List<Booking>>(emptyList())
    val userBookings: StateFlow<List<Booking>> = _userBookings.asStateFlow()

    private val _bookingState = MutableStateFlow<BookingState>(BookingState.Idle)
    val bookingState: StateFlow<BookingState> = _bookingState.asStateFlow()

    fun loadUserBookings(userId: Int) {
        viewModelScope.launch {
            bookingRepository.getBookingsByUser(userId).collect { bookings ->
                _userBookings.value = bookings
            }
        }
    }

    fun createBooking(
        userId: Int,
        roomId: Int,
        checkInDate: Long,
        checkOutDate: Long,
        totalPrice: Double
    ) {
        viewModelScope.launch {
            _bookingState.value = BookingState.Loading

            // Validaciones
            if (checkInDate >= checkOutDate) {
                _bookingState.value = BookingState.Error("La fecha de salida debe ser posterior a la de entrada")
                return@launch
            }

            if (checkInDate < System.currentTimeMillis()) {
                _bookingState.value = BookingState.Error("No se puede reservar en fechas pasadas")
                return@launch
            }

            try {
                val result = bookingRepository.createBooking(
                    userId = userId,
                    roomId = roomId,
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    totalPrice = totalPrice
                )

                result.fold(
                    onSuccess = {
                        _bookingState.value = BookingState.Success("Reserva creada exitosamente")
                    },
                    onFailure = { error ->
                        _bookingState.value = BookingState.Error(
                            error.message ?: "Error al crear la reserva"
                        )
                    }
                )
            } catch (e: Exception) {
                _bookingState.value = BookingState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun cancelBooking(bookingId: Int) {
        viewModelScope.launch {
            _bookingState.value = BookingState.Loading
            try {
                val result = bookingRepository.cancelBooking(bookingId)
                result.fold(
                    onSuccess = {
                        _bookingState.value = BookingState.Success("Reserva cancelada exitosamente")
                    },
                    onFailure = { error ->
                        _bookingState.value = BookingState.Error(
                            error.message ?: "Error al cancelar la reserva"
                        )
                    }
                )
            } catch (e: Exception) {
                _bookingState.value = BookingState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetBookingState() {
        _bookingState.value = BookingState.Idle
    }
}

sealed class BookingState {
    object Idle : BookingState()
    object Loading : BookingState()
    data class Success(val message: String) : BookingState()
    data class Error(val message: String) : BookingState()
}