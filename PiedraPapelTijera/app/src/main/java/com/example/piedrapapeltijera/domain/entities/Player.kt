package com.example.piedrapapeltijera.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_entity")
data class Player(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "score")
    val score: Int = 0,

    @ColumnInfo(name = "wins")
    val wins: Int = 0,

    @ColumnInfo(name = "losses")
    val losses: Int = 0,

    @ColumnInfo(name = "draws")
    val draws: Int = 0,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
) {
    /**
     * Calcula el total de partidas jugadas
     */
    fun getTotalGames(): Int = wins + losses + draws

    /**
     * Calcula el porcentaje de victorias
     */
    fun getWinRate(): Float {
        val total = getTotalGames()
        return if (total > 0) (wins.toFloat() / total.toFloat()) * 100 else 0f
    }

    /**
     * Retorna un resumen del jugador
     */
    fun getSummary(): String {
        return "$name: ${wins}V - ${losses}D - ${draws}E"
    }
}