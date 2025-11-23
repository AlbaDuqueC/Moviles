package com.example.ejercicio3examen.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ejercicio3examen.data.repositories.RepositoryPersona

class VMPersonaPeso : ViewModel() {

    //variable privada del tipo del repositorio para poder usar sus métodos
    private val _repositorioPersona = RepositoryPersona

    //get del peso
    fun getPesoPersona(): Double {
        return _repositorioPersona.getPesoPersona()
    }

    //set del peso
    fun setPesoPersona(peso: Double) {
        _repositorioPersona.setPesoPersona(peso)
    }

    //get del sexo
    fun getSexoPersona(): String {
        return _repositorioPersona.getSexoPersona()
    }

    //set del sexo
    fun setSexoPersona(sexo: String) {
        _repositorioPersona.setSexoPersona(sexo)
    }

}