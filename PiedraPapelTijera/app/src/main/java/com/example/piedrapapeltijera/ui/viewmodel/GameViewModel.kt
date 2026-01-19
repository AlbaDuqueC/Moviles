package com.example.piedrapapeltijera.ui.viewmodel

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.piedrapapeltijera.data.repositories.GameRepository
import com.example.piedrapapeltijera.data.repositories.PlayerRepository
import com.example.piedrapapeltijera.domain.entities.Player
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
import com.example.piedrapapeltijera.domain.valueObjects.Round
import com.example.piedrapapeltijera.ui.models.GameStateModel
import com.example.piedrapapeltijera.ui.models.RoundResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameViewModel(
    private val repository: PlayerRepository, // Inyección correcta del repositorio

    // UseCases instanciados por defecto (o puedes inyectarlos en el Factory si prefieres)
    private val initGameUseCase: IInitGameUseCase = InitGameUseCase(),
    private val playRoundUseCase: IPlayRoundUseCase = PlayRoundUseCase(),
    // NOTA: He simplificado GetAIChoiceUseCase. Si necesita un repo específico, deberías pasarlo en el Factory.
    // Asumo que tienes un constructor vacío o simple para este ejemplo:
    private val getAIChoiceUseCase: IGetAIChoiceUseCase = GetAIChoiceUseCase(

    ),
    private val updateGameStateUseCase: IUpdateGameStateUseCase = UpdateGameStateUseCase()
) : ViewModel() {

    // FLOW: Lista reactiva de jugadores desde la BD
    val lista: StateFlow<List<Player>> = repository.getAllPlayersFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Variables de estado del juego
    private var currentGame: Game? = null

    private val _gameState = MutableLiveData<GameStateModel>()
    val gameState: LiveData<GameStateModel> = _gameState

    private val _navigateToResult = MutableLiveData<Game?>()
    val navigateToResult: LiveData<Game?> = _navigateToResult

    // Ya no necesitamos _allPlayers LiveData manual porque usamos 'lista' (StateFlow)
    // Pero si necesitas cargar una lista única sin observar cambios:
    private val _manualPlayerList = MutableLiveData<List<Player>>()

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
            roundText = "${game.currentRound}/${game.totalRounds}",
            playerScoreText = "${game.player.score}",
            aiScoreText = "${game.aiScore}",
            lastRoundResult = lastRound?.let { mapRoundToModel(it) },
            isGameFinished = game.isFinished
        )
    }

    private fun mapRoundToModel(round: Round): RoundResultModel {
        return RoundResultModel(
            playerChoice = getChoiceEmoji(round.playerChoice),
            aiChoice = getChoiceEmoji(round.aiChoice),
            result = when (round.result) {
                GameResult.GANAR -> "¡GANASTE! 🎉"
                GameResult.PERDER -> "PERDISTE 😢"
                GameResult.EMPATE -> "EMPATE ⚖️"
            },
            resultColor = when (round.result) {
                GameResult.GANAR -> Color.parseColor("#4CAF50")
                GameResult.PERDER -> Color.parseColor("#F44336")
                GameResult.EMPATE -> Color.parseColor("#FF9800")
            }
        )
    }

    private fun getChoiceEmoji(choice: GameChoice): String {
        return when (choice) {
            GameChoice.PIEDRA -> "🪨"
            GameChoice.PAPEL -> "📄"
            GameChoice.TIJERAS -> "✂️"
        }
    }

    fun onNavigatedToResult() {
        _navigateToResult.value = null
    }

    // =============== FUNCIONES BASE DE DATOS ===============

    fun saveGameToDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val game = currentGame ?: return@launch

                // Calcular estadísticas
                var wins = 0
                var losses = 0
                var draws = 0

                game.rounds.forEach { round ->
                    when (round.result) {
                        GameResult.GANAR -> wins++
                        GameResult.PERDER -> losses++
                        GameResult.EMPATE -> draws++
                    }
                }

                // Verificar si el jugador ya existe para actualizarlo o crear uno nuevo
                val existingPlayer = repository.getPlayerByName(game.player.name)

                val playerToSave = if (existingPlayer != null) {
                    existingPlayer.copy(
                        score = existingPlayer.score + game.player.score,
                        wins = existingPlayer.wins + wins,
                        losses = existingPlayer.losses + losses,
                        draws = existingPlayer.draws + draws
                    )
                } else {
                    Player(
                        name = game.player.name,
                        score = game.player.score,
                        wins = wins,
                        losses = losses,
                        draws = draws
                    )
                }

                repository.insertPlayer(playerToSave) // El repositorio manejará insert o replace según tu DAO

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPlayer(player)
        }
    }

    fun updatePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayer(player)
        }
    }

    fun deletePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlayer(player)
        }
    }

    fun deleteAllPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPlayers()
        }
    }
}