package com.example.piedrapapeltijera.domain.useCases

import com.example.piedrapapeltijera.domain.interfaces.IPlayRoundUseCase
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import com.example.piedrapapeltijera.domain.valueObjects.GameResult
import com.example.piedrapapeltijera.domain.valueObjects.Round

class PlayRoundUseCase : IPlayRoundUseCase {

    override fun execute(playerChoice: GameChoice, aiChoice: GameChoice): Round {
        val result = when {
            playerChoice == aiChoice -> GameResult.EMPATE
            playerChoice.winsAgainst(aiChoice) -> GameResult.GANAR
            else -> GameResult.PERDER
        }

        return Round(
            playerChoice = playerChoice,
            aiChoice = aiChoice,
            result = result
        )
    }
}