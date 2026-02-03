package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.BookingRepository
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.ui.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val bookingRepository: BookingRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _userBookings = MutableStateFlow<List<BookingWithDetails>>(emptyList())
    val userBookings: StateFlow<List<BookingWithDetails>> = _userBookings.asStateFlow()

    private val _cancelState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val cancelState: StateFlow<UiState<Unit>> = _cancelState.asStateFlow()

    fun loadUserBookings(userId: Int) {
        viewModelScope.launch {
            bookingRepository.getUserBookings(userId).collect { bookings ->
                _userBookings.value = bookings
            }
        }
    }

    fun cancelBooking(bookingId: Int) {
        viewModelScope.launch {
            _cancelState.value = UiState.Loading

            val result = bookingRepository.cancelBooking(bookingId)

            result.fold(
                onSuccess = {
                    _cancelState.value = UiState.Success(Unit)
                },
                onFailure = { exception ->
                    _cancelState.value = UiState.Error(
                        exception.message ?: "Error al cancelar la reserva"
                    )
                }
            )
        }
    }

    fun resetCancelState() {
        _cancelState.value = UiState.Idle
    }
}