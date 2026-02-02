package com.example.hostaldigital.data.repository


import com.example.hostaldigital.domain.dao.RoomDao
import com.example.hostaldigital.domain.entities.RoomEntity
import com.example.hostaldigital.ui.model.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomRepository(private val roomDao: RoomDao) {

    fun getAvailableRooms(): Flow<List<Room>> {
        return roomDao.getAvailableRooms().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    fun getAllRooms(): Flow<List<Room>> {
        return roomDao.getAllRooms().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun getRoomById(roomId: Int): Room? {
        return roomDao.getRoomById(roomId)?.toDomain()
    }

    suspend fun addRoom(
        roomNumber: String,
        type: String,
        price: Double,
        capacity: Int,
        description: String
    ): Result<Long> {
        return try {
            val roomId = roomDao.insert(
                RoomEntity(
                    roomNumber = roomNumber,
                    type = type,
                    price = price,
                    capacity = capacity,
                    description = description,
                    isAvailable = true
                )
            )
            Result.success(roomId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateRoomAvailability(roomId: Int, isAvailable: Boolean) {
        roomDao.updateRoomAvailability(roomId, isAvailable)
    }

    suspend fun getRoomCount(): Int {
        return roomDao.getRoomCount()
    }

    private fun RoomEntity.toDomain() = Room(
        id = id,
        roomNumber = roomNumber,
        type = type,
        price = price,
        capacity = capacity,
        description = description,
        isAvailable = isAvailable
    )
}