package com.example.piedrapapeltijera.data.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.piedrapapeltijera.data.repositories.PlayerRepository
import com.example.piedrapapeltijera.ui.viewmodel.GameViewModel

// El Factory debe recibir el repositorio que necesita el ViewModel
class GameFactory(private val repository: PlayerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}