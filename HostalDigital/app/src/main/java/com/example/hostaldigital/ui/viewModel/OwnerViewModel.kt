package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.BookingRepository
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.data.repository.UserRepository
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.domain.entities.RoomEntity
import com.example.hostaldigital.ui.model.Room
import com.example.hostaldigital.ui.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


fun Room.toEntity() = RoomEntity(
    id = id,
    roomNumber = roomNumber,
    type = type,
    price = price,
    capacity = capacity,
    description = description,
    isAvailable = isAvailable
)

// --- VIEWMODEL ---

class OwnerViewModel(
    private val roomRepository: RoomRepository,
    private val bookingRepository: BookingRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _allRooms = MutableStateFlow<List<Room>>(emptyList())
    val allRooms: StateFlow<List<Room>> = _allRooms.asStateFlow()

    private val _allBookings = MutableStateFlow<List<BookingWithDetails>>(emptyList())
    val allBookings: StateFlow<List<BookingWithDetails>> = _allBookings.asStateFlow()

    private val _roomBookings = MutableStateFlow<List<BookingWithDetails>>(emptyList())
    val roomBookings: StateFlow<List<BookingWithDetails>> = _roomBookings.asStateFlow()

    private val _addRoomState = MutableStateFlow<UiState<Long>>(UiState.Idle)
    val addRoomState: StateFlow<UiState<Long>> = _addRoomState.asStateFlow()

    private val _completeBookingState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val completeBookingState: StateFlow<UiState<Unit>> = _completeBookingState.asStateFlow()

    init {
        loadAllRooms()
        loadAllBookings()
    }

    private fun loadAllRooms() {
        viewModelScope.launch {
            roomRepository.getAllRooms().collect { entities ->
                // Usamos la función de extensión para convertir la lista
                _allRooms.value = entities.map { it.toUiModel() }
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

    fun loadRoomBookings(roomId: Int) {
        viewModelScope.launch {
            // Asegúrate de que BookingRepository tenga esta función definida
            bookingRepository.getRoomBookings(roomId).collect { bookings ->
                _roomBookings.value = bookings
            }
        }
    }

    fun addRoom(room: Room) {
        viewModelScope.launch {
            _addRoomState.value = UiState.Loading
            // Convertimos el modelo de la UI a Entidad antes de enviarlo al Repositorio
            val result = roomRepository.addRoom(room.toEntity())

            result.fold(
                onSuccess = { roomId ->
                    _addRoomState.value = UiState.Success(roomId)
                },
                onFailure = { exception ->
                    _addRoomState.value = UiState.Error(
                        exception.message ?: "Error al agregar habitación"
                    )
                }
            )
        }
    }

    fun completeBooking(bookingId: Int) {
        viewModelScope.launch {
            _completeBookingState.value = UiState.Loading
            val result = bookingRepository.completeBooking(bookingId)

            result.fold(
                onSuccess = {
                    _completeBookingState.value = UiState.Success(Unit)
                },
                onFailure = { exception ->
                    _completeBookingState.value = UiState.Error(
                        exception.message ?: "Error al completar la reserva"
                    )
                }
            )
        }
    }

    fun resetAddRoomState() {
        _addRoomState.value = UiState.Idle
    }

    fun resetCompleteBookingState() {
        _completeBookingState.value = UiState.Idle
    }
}