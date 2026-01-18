package com.example.piedrapapeltijera.domain.useCases

import com.example.piedrapapeltijera.domain.interfaces.IUpdateGameStateUseCase
import com.example.piedrapapeltijera.domain.valueObjects.Game
import com.example.piedrapapeltijera.domain.valueObjects.GameResult
import com.example.piedrapapeltijera.domain.valueObjects.Round

class UpdateGameStateUseCase : IUpdateGameStateUseCase {

    override fun execute(currentGame: Game, round: Round): Game {
        val newPlayerScore = currentGame.player.score +
                if (round.result == GameResult.GANAR) 1 else 0
        val newAiScore = currentGame.aiScore +
                if (round.result == GameResult.PERDER) 1 else 0

        val updatedPlayer = currentGame.player.copy(score = newPlayerScore)

        return currentGame.copy(
            player = updatedPlayer,
            currentRound = currentGame.currentRound + 1,
            aiScore = newAiScore,
            rounds = currentGame.rounds + round
        )
    }
}