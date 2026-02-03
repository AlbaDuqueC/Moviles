package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.domain.entities.RoomEntity
import com.example.hostaldigital.ui.model.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoomListViewModel(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _availableRooms = MutableStateFlow<List<RoomEntity>>(emptyList())
    val availableRooms: StateFlow<List<RoomEntity>> = _availableRooms.asStateFlow()

    private val _selectedRoom = MutableStateFlow<Room?>(null)
    val selectedRoom: StateFlow<Room?> = _selectedRoom.asStateFlow()

    init {
        loadAvailableRooms()
    }

    private fun loadAvailableRooms() {
        viewModelScope.launch {
            roomRepository.getAvailableRooms().collect { rooms ->
                _availableRooms.value = rooms
            }
        }
    }

    fun selectRoom(room: Room) {
        _selectedRoom.value = room
    }

    fun clearSelectedRoom() {
        _selectedRoom.value = null
    }

    suspend fun getRoomById(roomId: Int): Room? {
        return roomRepository.getRoomById(roomId) as Room?
    }
}