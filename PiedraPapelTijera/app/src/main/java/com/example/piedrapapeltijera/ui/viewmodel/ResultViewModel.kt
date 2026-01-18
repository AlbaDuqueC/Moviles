package com.example.piedrapapeltijera.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.piedrapapeltijera.domain.valueObjects.Game

class ResultViewModel : ViewModel() {

    private val _game = MutableLiveData<Game>()
    val game: LiveData<Game> = _game

    private val _winnerText = MutableLiveData<String>()
    val winnerText: LiveData<String> = _winnerText

    private val _scoreText = MutableLiveData<String>()
    val scoreText: LiveData<String> = _scoreText

    private val _navigateToGame = MutableLiveData<Boolean>()
    val navigateToGame: LiveData<Boolean> = _navigateToGame

    private val _navigateToWelcome = MutableLiveData<Boolean>()
    val navigateToWelcome: LiveData<Boolean> = _navigateToWelcome

    fun setGame(game: Game) {
        _game.value = game

        _winnerText.value = when (game.winner) {
            "EMPATE" -> "¡Es un empate! 🤝"
            "JARVIS" -> "¡JARVIS (IA) ha ganado! 🤖"
            else -> "¡${game.winner} ha ganado! 🏆"
        }

        _scoreText.value = "${game.player.name}: ${game.player.score} - JARVIS: ${game.aiScore}"
    }

    fun playAgain() {
        _navigateToGame.value = true
    }

    fun exitToWelcome() {
        _navigateToWelcome.value = true
    }

    fun onNavigated() {
        _navigateToGame.value = false
        _navigateToWelcome.value = false
    }
}