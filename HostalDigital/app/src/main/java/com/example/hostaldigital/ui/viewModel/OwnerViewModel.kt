package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.BookingRepository
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.ui.model.Booking
import com.example.hostaldigital.ui.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OwnerViewModel(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _allRooms = MutableStateFlow<List<Room>>(emptyList())
    val allRooms: StateFlow<List<Room>> = _allRooms.asStateFlow()

    private val _allBookings = MutableStateFlow<List<Booking>>(emptyList())
    val allBookings: StateFlow<List<Booking>> = _allBookings.asStateFlow()

    private val _activeBookings = MutableStateFlow<List<Booking>>(emptyList())
    val activeBookings: StateFlow<List<Booking>> = _activeBookings.asStateFlow()

    private val _ownerState = MutableStateFlow<OwnerState>(OwnerState.Idle)
    val ownerState: StateFlow<OwnerState> = _ownerState.asStateFlow()

    init {
        loadAllRooms()
        loadAllBookings()
        loadActiveBookings()
    }

    private fun loadAllRooms() {
        viewModelScope.launch {
            roomRepository.getAllRooms().collect { rooms ->
                _allRooms.value = rooms
            }
        }
    }

    private fun loadAllBookings() {
        viewModelScope.launch {
            bookingRepository.getAllBookings().collect { bookings ->
                _allBookings.value = bookings
            }
        }
    }

    private fun loadActiveBookings() {
        viewModelScope.launch {
            bookingRepository.getActiveBookings().collect { bookings ->
                _activeBookings.value = bookings
            }
        }
    }

    fun addRoom(
        roomNumber: String,
        type: String,
        price: Double,
        capacity: Int,
        description: String
    ) {
        viewModelScope.launch {
            _ownerState.value = OwnerState.Loading

            // Validaciones
            if (roomNumber.isBlank()) {
                _ownerState.value = OwnerState.Error("El número de habitación no puede estar vacío")
                return@launch
            }

            if (price <= 0) {
                _ownerState.value = OwnerState.Error("El precio debe ser mayor a 0")
                return@launch
            }

            if (capacity <= 0) {
                _ownerState.value = OwnerState.Error("La capacidad debe ser mayor a 0")
                return@launch
            }

            try {
                val result = roomRepository.addRoom(roomNumber, type, price, capacity, description)
                result.fold(
                    onSuccess = {
                        _ownerState.value = OwnerState.Success("Habitación añadida exitosamente")
                    },
                    onFailure = { error ->
                        _ownerState.value = OwnerState.Error(
                            error.message ?: "Error al añadir la habitación"
                        )
                    }
                )
            } catch (e: Exception) {
                _ownerState.value = OwnerState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun makeRoomAvailable(roomId: Int) {
        viewModelScope.launch {
            _ownerState.value = OwnerState.Loading
            try {
                roomRepository.updateRoomAvailability(roomId, true)
                _ownerState.value = OwnerState.Success("Habitación disponible")
            } catch (e: Exception) {
                _ownerState.value = OwnerState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun completeBooking(bookingId: Int) {
        viewModelScope.launch {
            _ownerState.value = OwnerState.Loading
            try {
                val result = bookingRepository.completeBooking(bookingId, makeRoomAvailable = true)
                result.fold(
                    onSuccess = {
                        _ownerState.value = OwnerState.Success("Reserva finalizada y habitación liberada")
                    },
                    onFailure = { error ->
                        _ownerState.value = OwnerState.Error(
                            error.message ?: "Error al finalizar la reserva"
                        )
                    }
                )
            } catch (e: Exception) {
                _ownerState.value = OwnerState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun getBookingsByRoom(roomId: Int): StateFlow<List<Booking>> {
        val bookingsFlow = MutableStateFlow<List<Booking>>(emptyList())
        viewModelScope.launch {
            bookingRepository.getBookingsByRoom(roomId).collect { bookings ->
                bookingsFlow.value = bookings
            }
        }
        return bookingsFlow.asStateFlow()
    }

    fun resetOwnerState() {
        _ownerState.value = OwnerState.Idle
    }
}

sealed class OwnerState {
    object Idle : OwnerState()
    object Loading : OwnerState()
    data class Success(val message: String) : OwnerState()
    data class Error(val message: String) : OwnerState()
}