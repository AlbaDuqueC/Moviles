package com.example.piedrapapeltijera.domain.valueObjects
import com.example.piedrapapeltijera.domain.entities.Player

data class Game(
    val player: Player,
    val totalRounds: Int,
    val currentRound: Int = 0,
    val aiScore: Int = 0,
    val rounds: List<Round> = emptyList()
) {
    val isFinished: Boolean
        get() = currentRound >= totalRounds

    val winner: String?
        get() = when {
            !isFinished -> null
            player.score > aiScore -> player.name
            aiScore > player.score -> "Maquina"
            else -> "EMPATE"
        }
}
