package com.example.piedrapapeltijera.data.repositories

import com.example.piedrapapeltijera.domain.entities.Player
import com.example.piedrapapeltijera.domain.interfaces.DAO.PlayerDao
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {

    // Cambiado a Flow para usar con stateIn en el ViewModel
    fun getAllPlayersFlow(): Flow<List<Player>> {
        return playerDao.getAllPlayersFlow()
    }

    suspend fun getAllPlayers(): List<Player> {
        return playerDao.getAllPlayer()
    }

    suspend fun insertPlayer(player: Player) {
        playerDao.insert(player)
    }

    suspend fun updatePlayer(player: Player) {
        playerDao.update(player)
    }

    suspend fun deletePlayer(player: Player) {
        playerDao.delete(player)
    }

    suspend fun deleteAllPlayers() {
        playerDao.deleteAll()
    }

    suspend fun getPlayerByName(name: String): Player? {
        return playerDao.getPlayerByName(name)
    }
}