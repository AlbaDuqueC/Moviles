package com.example.hostaldigital.domain.entities

import androidx.room.Embedded
import androidx.room.Relation

data class BookingWithDetails(
    @Embedded val booking: BookingEntity,
    @Relation(
        parentColumn = "roomId",
        entityColumn = "id"
    )
    val room: RoomEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val user: UserEntity
)