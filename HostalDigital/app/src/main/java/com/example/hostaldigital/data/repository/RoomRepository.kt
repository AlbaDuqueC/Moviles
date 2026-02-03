package com.example.hostaldigital.data.repository


import com.example.hostaldigital.domain.dao.RoomDao
import com.example.hostaldigital.domain.entities.RoomEntity
import kotlinx.coroutines.flow.Flow

class RoomRepository(private val roomDao: RoomDao) {

    fun getAllRooms(): Flow<List<RoomEntity>> {
        return roomDao.getAllRooms()
    }

    fun getAvailableRooms(): Flow<List<RoomEntity>> {
        return roomDao.getAvailableRooms()
    }

    suspend fun getRoomById(roomId: Int): RoomEntity? {
        return roomDao.getRoomById(roomId)
    }

    suspend fun addRoom(room: RoomEntity): Result<Long> {
        return try {
            val roomId = roomDao.insert(room)
            Result.success(roomId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateRoom(room: RoomEntity): Result<Unit> {
        return try {
            roomDao.update(room)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateRoomAvailability(roomId: Int, isAvailable: Boolean): Result<Unit> {
        return try {
            roomDao.updateRoomAvailability(roomId, isAvailable)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRoomCount(): Int {
        return roomDao.getRoomCount()
    }
}