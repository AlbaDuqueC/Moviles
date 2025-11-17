package com.example.midiariodeviajes.presentation.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.example.midiariodeviajes.domain.Entities.Destino
import com.example.midiariodeviajes.domain.Reposirories.DestinoRepository

class DestinoViewModel(navController: NavHostController) {

    private val _repo= DestinoRepository
    private val _destinos = mutableStateOf<List<Destino>>(emptyList())

    val destinos: State<List<Destino>> get() = _destinos

    init {
        loadDestinos()
    }

    private fun loadDestinos(){
        _destinos.value=_repo.getAllDestino()

    }

    fun insertDestino(destino: Destino){

        _repo.insert(destino)
        loadDestinos()

    }


}