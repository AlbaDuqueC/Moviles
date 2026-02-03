package com.example.hostaldigital.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// IMPORTANTE: Quitamos el import de Room aquí para evitar dependencias circulares

@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "roomNumber")
    val roomNumber: String,

    @ColumnInfo(name = "type")
    val type: String, // Single, Double, Suite

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "capacity")
    val capacity: Int,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "isAvailable")
    val isAvailable: Boolean = true
)