package com.example.piedrapapeltijera.domain.interfaces

import com.example.piedrapapeltijera.domain.valueObjects.Game
import com.example.piedrapapeltijera.domain.valueObjects.Round

interface IUpdateGameStateUseCase {
    fun execute(currentGame: Game, round: Round): Game
}