package com.example.boletn2_jpcnavegacin.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.boletn2_jpcnavegacin.data.repositories.DestinationRepository
import com.example.boletn2_jpcnavegacin.domain.entities.TravelDestination

class VMDestination : ViewModel() {

    //variable que igualará al repositorio para usar sus métodos
    private val _destinationRepository = DestinationRepository

    //variable que almacenará una lista de los destinos
    private val _destinations = mutableStateOf<List<TravelDestination>>(emptyList())
    //variable de tipo lista de destinos que almacena lo que haya en destinations
    val destinations : State<List<TravelDestination>> get() = _destinations
    //constructor
    init {
        loadDestinations()
    }

    //funcion que se encarga de igualar el valor de los destinos a todos ellos
    private fun loadDestinations() {
        _destinations.value = _destinationRepository.getAllDestinations()
    }


}