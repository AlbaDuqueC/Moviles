package com.example.piedrapapeltijera.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.piedrapapeltijera.domain.interfaces.IValidatePlayerNameUseCase
import com.example.piedrapapeltijera.domain.useCases.ValidatePlayerNameUseCase

class WelcomeViewModel(
    private val validatePlayerNameUseCase: IValidatePlayerNameUseCase = ValidatePlayerNameUseCase()
) : ViewModel() {

    private val _playerName = MutableLiveData<String>()
    val playerName: LiveData<String> = _playerName

    private val _navigateToGame = MutableLiveData<Boolean>()
    val navigateToGame: LiveData<Boolean> = _navigateToGame

    private val _showError = MutableLiveData<String?>()
    val showError: LiveData<String?> = _showError

    fun setPlayerName(name: String) {
        _playerName.value = name
    }

    fun startGame() {
        val name = _playerName.value.orEmpty()

        if (validatePlayerNameUseCase.execute(name)) {
            _navigateToGame.value = true
        } else {
            _showError.value = "Por favor, ingresa un nombre válido (mínimo 2 caracteres)"
        }
    }

    fun onNavigatedToGame() {
        _navigateToGame.value = false
    }

    fun onErrorShown() {
        _showError.value = null
    }
}