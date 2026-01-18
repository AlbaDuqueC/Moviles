package com.example.piedrapapeltijera.ui.models

data class GameStateModel(
    val playerName: String,
    val aiName: String = "🤖 Maquina",
    val roundText: String,
    val playerScoreText: String,
    val aiScoreText: String,
    val lastRoundResult: RoundResultModel?,
    val isGameFinished: Boolean
)