package com.example.hostaldigital.domain.dao

import androidx.room.*
import com.example.hostaldigital.domain.entities.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    // Da un listado de las habitaciones disponibles
    @Query("SELECT * FROM rooms WHERE isAvailable = 1 ORDER BY roomNumber")
    fun getAvailableRooms(): Flow<List<RoomEntity>>

    // Da todas las habitaciones
    @Query("SELECT * FROM rooms ORDER BY roomNumber")
    fun getAllRooms(): Flow<List<RoomEntity>>

    // Da una habitacion dependiendo del id
    @Query("SELECT * FROM rooms WHERE id = :roomId")
    suspend fun getRoomById(roomId: Int): RoomEntity?

    // Inserta una habitacion
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(room: RoomEntity): Long

    // Inserta varias habitaciones
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(rooms: List<RoomEntity>)

    // modifica una habitacion
    @Update
    suspend fun update(room: RoomEntity)

    // Cambia una especificacion especifica
    @Query("UPDATE rooms SET isAvailable = :isAvailable WHERE id = :roomId")
    suspend fun updateRoomAvailability(roomId: Int, isAvailable: Boolean)

    // Cuentas cuantas habitaciones hay
    @Query("SELECT COUNT(*) FROM rooms")
    suspend fun getRoomCount(): Int
}