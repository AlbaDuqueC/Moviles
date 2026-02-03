package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.BookingRepository
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.domain.entities.BookingEntity
import com.example.hostaldigital.domain.entities.RoomEntity
import com.example.hostaldigital.ui.model.Room
import com.example.hostaldigital.ui.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

// Función de extensión para convertir Entity a Modelo de UI
fun RoomEntity.toUiModel() = Room(
    id = id,
    roomNumber = roomNumber,
    type = type,
    price = price,
    capacity = capacity,
    description = description,
    isAvailable = isAvailable
)

class RoomViewModel(
    private val roomRepository: RoomRepository,
    private val bookingRepository: BookingRepository
) : ViewModel() {

    private val _availableRooms = MutableStateFlow<List<Room>>(emptyList())
    val availableRooms: StateFlow<List<Room>> = _availableRooms.asStateFlow()

    private val _selectedRoom = MutableStateFlow<Room?>(null)
    val selectedRoom: StateFlow<Room?> = _selectedRoom.asStateFlow()

    private val _bookingState = MutableStateFlow<UiState<Long>>(UiState.Idle)
    val bookingState: StateFlow<UiState<Long>> = _bookingState.asStateFlow()

    init {
        loadAvailableRooms()
    }

    private fun loadAvailableRooms() {
        viewModelScope.launch {
            roomRepository.getAvailableRooms().collect { entities ->
                // Convertimos la lista de la DB a la lista de la UI
                _availableRooms.value = entities.map { it.toUiModel() }
            }
        }
    }

    fun selectRoom(room: Room) {
        _selectedRoom.value = room
    }

    fun clearSelectedRoom() {
        _selectedRoom.value = null
    }

    fun bookRoom(
        userId: Int,
        roomId: Int,
        checkInDate: Long,
        checkOutDate: Long,
        status: String
    ) {
        viewModelScope.launch {
            _bookingState.value = UiState.Loading

            val roomEntity = roomRepository.getRoomById(roomId)
            if (roomEntity == null) {
                _bookingState.value = UiState.Error("Habitación no encontrada")
                return@launch
            }

            // Cálculo de noches y precio
            val diff = checkOutDate - checkInDate
            val nights = TimeUnit.MILLISECONDS.toDays(diff).coerceAtLeast(1)
            val totalPrice = roomEntity.price * nights

            // CREACIÓN LIMPIA: Solo usamos los campos que existen en tu BookingEntity
            val booking = BookingEntity(
                userId = userId,
                roomId = roomId,
                checkInDate = checkInDate,
                checkOutDate = checkOutDate,
                totalPrice = totalPrice,
                status = status
            )

            val result = bookingRepository.createBooking(booking)

            // .fold() corregido sin parámetros nombrados para evitar errores
            result.fold(
                { id ->
                    _bookingState.value = UiState.Success(id)
                },
                { error ->
                    _bookingState.value = UiState.Error(error.message ?: "Error al reservar")
                }
            )
        }
    }

    fun resetBookingState() {
        _bookingState.value = UiState.Idle
    }
}