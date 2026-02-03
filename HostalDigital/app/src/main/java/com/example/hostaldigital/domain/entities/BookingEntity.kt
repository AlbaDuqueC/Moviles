package com.example.hostaldigital.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "bookings",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "userId")
    val userId: Int,

    @ColumnInfo(name = "roomId")
    val roomId: Int,

    @ColumnInfo(name = "checkInDate")
    val checkInDate: Long,

    @ColumnInfo(name = "checkOutDate")
    val checkOutDate: Long,

    @ColumnInfo(name = "totalPrice")
    val totalPrice: Double,

    @ColumnInfo(name = "status")
    val status: String, // "ACTIVE", "COMPLETED", "CANCELLED"

    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis() // Esta es la columna que faltaba
)