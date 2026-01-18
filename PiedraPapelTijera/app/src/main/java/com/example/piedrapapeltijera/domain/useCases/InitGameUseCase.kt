package com.example.piedrapapeltijera.domain.useCases

import com.example.piedrapapeltijera.domain.entities.Player
import com.example.piedrapapeltijera.domain.interfaces.IInitGameUseCase
import com.example.piedrapapeltijera.domain.valueObjects.Game

class InitGameUseCase : IInitGameUseCase {

    override fun execute(playerName: String, totalRounds: Int): Game {
        val player = Player(name = playerName)
        return Game(
            player = player,
            totalRounds = totalRounds
        )
    }
}