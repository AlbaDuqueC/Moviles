package com.example.piedrapapeltijera.domain.interfaces

import com.example.piedrapapeltijera.domain.valueObjects.Game

interface IInitGameUseCase {
    fun execute(playerName: String, totalRounds: Int = 3): Game
}