package com.example.hostaldigital.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


// Entidad de tu historial
@Entity(
    tableName = "bookings",
    foreignKeys = [

        // ForeignKey del id del usuario
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        // ForeignKey del id de la habitacion
        ForeignKey(
            entity = RoomEntity::class,
            parentColumns = ["id"],
            childColumns = ["roomId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId"), Index("roomId")]
)
data class BookingEntity(

    // Id del historial
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    // id del usuario
    @ColumnInfo("userId")
    val userId: Int,

    // id de la habitacion
    @ColumnInfo("roomId")
    val roomId: Int,

    @ColumnInfo("checkInDate")
    val checkInDate: Long,

    @ColumnInfo("checkOutDate")
    val checkOutDate: Long,

    @ColumnInfo("totalPrice")
    val totalPrice: Double,

    @ColumnInfo("status")
    val status: String, // ACTIVE, CANCELLED, COMPLETED

    @ColumnInfo("createdAt")
    val createdAt: Long = System.currentTimeMillis()
)