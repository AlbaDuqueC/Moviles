package com.example.piedrapapeltijera.domain.valueObjects

data class Round(
    val playerChoice: GameChoice,
    val aiChoice: GameChoice,
    val result: GameResult
)
