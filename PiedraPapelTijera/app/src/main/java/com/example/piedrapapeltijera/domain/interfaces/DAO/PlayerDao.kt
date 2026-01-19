package com.example.piedrapapeltijera.domain.interfaces.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.piedrapapeltijera.domain.entities.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

        /**
         * Obtiene todos los jugadores ordenados por nombre
         */
        @Query("SELECT * FROM player_entity ORDER BY name ASC")
        suspend fun getAllPlayer(): List<Player>

        /**
         * Obtiene todos los jugadores ordenados por victorias (ranking)
         */
        @Query("SELECT * FROM player_entity ORDER BY wins DESC")
        suspend fun getPlayersByWins(): List<Player>

        /**
         * Obtiene todos los jugadores ordenados por score (ranking)
         */
        @Query("SELECT * FROM player_entity ORDER BY score DESC")
        suspend fun getPlayersByScore(): List<Player>

        /**
         * Busca un jugador por nombre
         */
        @Query("SELECT * FROM player_entity WHERE name = :name LIMIT 1")
        suspend fun getPlayerByName(name: String): Player?

        /**
         * Inserta un nuevo jugador
         * Si ya existe, lo reemplaza (REPLACE)
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(player: Player)

        /**
         * Inserta múltiples jugadores
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(players: List<Player>)

        /**
         * Actualiza un jugador existente
         */
        @Update
        suspend fun update(player: Player)

        /**
         * Elimina un jugador
         */
        @Delete
        suspend fun delete(player: Player)

        /**
         * Elimina todos los jugadores
         */
        @Query("DELETE FROM player_entity")
        suspend fun deleteAll()

        /**
         * Cuenta el total de jugadores
         */
        @Query("SELECT COUNT(*) FROM player_entity")
        suspend fun getPlayerCount(): Int

        /**
         * Obtiene el top 10 de jugadores por victorias
         */
        @Query("SELECT * FROM player_entity ORDER BY wins DESC LIMIT 10")
        suspend fun getTopPlayers(): List<Player>

        /**
         * Obtiene el top 10 de jugadores por score
         */
        @Query("SELECT * FROM player_entity ORDER BY score DESC LIMIT 10")
        suspend fun getTopPlayersByScore(): List<Player>

        @Query("SELECT * FROM player_entity")
        fun obtenerTareas(): Flow<List<Player>>

        @Query("SELECT * FROM player_entity ORDER BY name ASC") // Ajusta el query a tu tabla real
        fun getAllPlayersFlow(): Flow<List<Player>>



}