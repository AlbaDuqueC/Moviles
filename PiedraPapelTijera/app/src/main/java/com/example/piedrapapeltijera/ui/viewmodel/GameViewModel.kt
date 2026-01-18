package com.example.piedrapapeltijera.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.piedrapapeltijera.data.repositories.GameRepository
import com.example.piedrapapeltijera.domain.interfaces.IGetAIChoiceUseCase
import com.example.piedrapapeltijera.domain.interfaces.IInitGameUseCase
import com.example.piedrapapeltijera.domain.interfaces.IPlayRoundUseCase
import com.example.piedrapapeltijera.domain.interfaces.IUpdateGameStateUseCase
import com.example.piedrapapeltijera.domain.useCases.GetAIChoiceUseCase
import com.example.piedrapapeltijera.domain.useCases.InitGameUseCase
import com.example.piedrapapeltijera.domain.useCases.PlayRoundUseCase
import com.example.piedrapapeltijera.domain.useCases.UpdateGameStateUseCase
import com.example.piedrapapeltijera.domain.valueObjects.Game
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import com.example.piedrapapeltijera.domain.valueObjects.GameResult
import com.example.piedrapapeltijera.ui.models.GameStateModel
import com.example.piedrapapeltijera.ui.models.RoundResultModel

class GameViewModel(
    private val initGameUseCase: IInitGameUseCase = InitGameUseCase(),
    private val playRoundUseCase: IPlayRoundUseCase = PlayRoundUseCase(),
    private val getAIChoiceUseCase: IGetAIChoiceUseCase = GetAIChoiceUseCase(GameRepository()),
    private val updateGameStateUseCase: IUpdateGameStateUseCase = UpdateGameStateUseCase()
) : ViewModel() {

    private var currentGame: Game? = null

    private val _gameState = MutableLiveData<GameStateModel>()
    val gameState: LiveData<GameStateModel> = _gameState

    private val _navigateToResult = MutableLiveData<Game?>()
    val navigateToResult: LiveData<Game?> = _navigateToResult

    fun initGame(playerName: String, totalRounds: Int = 3) {
        currentGame = initGameUseCase.execute(playerName, totalRounds)
        updateGameStateView()
    }

    fun playerChoose(choice: GameChoice, isRandom: Boolean = false) {
        val game = currentGame ?: return

        val playerChoice = if (isRandom) {
            getAIChoiceUseCase.execute()
        } else {
            choice
        }

        val aiChoice = getAIChoiceUseCase.execute()
        val round = playRoundUseCase.execute(playerChoice, aiChoice)

        currentGame = updateGameStateUseCase.execute(game, round)
        updateGameStateView()

        if (currentGame?.isFinished == true) {
            _navigateToResult.value = currentGame
        }
    }

    private fun updateGameStateView() {
        val game = currentGame ?: return
        val lastRound = game.rounds.lastOrNull()

        _gameState.value = GameStateModel(
            playerName = "👤 ${game.player.name}",
            roundText = "Ronda: ${game.currentRound}/${game.totalRounds}",
            playerScoreText = "Puntuación: ${game.player.score}",
            aiScoreText = "IA: ${game.aiScore}",
            lastRoundResult = lastRound?.let { mapRoundToModel(it) },
            isGameFinished = game.isFinished
        )
    }

    private fun mapRoundToModel(round: com.example.piedrapapeltijera.domain.valueObjects.Round): RoundResultModel {
        return RoundResultModel(
            playerChoice = "Tu elección: ${getChoiceEmoji(round.playerChoice)}",
            aiChoice = "IA eligió: ${getChoiceEmoji(round.aiChoice)}",
            result = when (round.result) {
                GameResult.GANAR -> "¡Ganaste esta ronda! :)"
                GameResult.PERDER -> "Perdiste esta ronda :'("
                GameResult.EMPATE -> "¡Empate! "
            },
            resultColor = when (round.result) {
                GameResult.GANAR -> android.graphics.Color.parseColor("#2E7D32")
                GameResult.PERDER -> android.graphics.Color.parseColor("#C62828")
                GameResult.EMPATE -> android.graphics.Color.parseColor("#F57C00")
            }
        )
    }

    private fun getChoiceEmoji(choice: GameChoice): String {
        return when (choice) {
            GameChoice.PIEDRA -> "🪨 Piedra"
            GameChoice.PAPEL -> "📄 Papel"
            GameChoice.TIJERAS -> "✂️ Tijeras"
        }
    }

    fun onNavigatedToResult() {
        _navigateToResult.value = null
    }
}