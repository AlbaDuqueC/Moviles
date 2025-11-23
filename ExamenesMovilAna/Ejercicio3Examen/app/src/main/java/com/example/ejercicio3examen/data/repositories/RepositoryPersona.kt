package com.example.ejercicio3examen.data.repositories

object RepositoryPersona {

    //variable que almacena el peso de la persona inicializado a 0
    private var _pesoPersona: Double = 0.0
    //almacena el sexo de la persona
    private var _sexoPersona: String = ""

    //funcion que devolverá el peso de la persona
    fun getPesoPersona(): Double {
        return _pesoPersona
    }

    //función para actualizar el peso de la persona
    fun setPesoPersona(peso: Double) {
        _pesoPersona = peso
    }

    //funcion que devolverá el sexo de la persona
    fun getSexoPersona(): String {
        return _sexoPersona
    }

    //función para actualizar el sexo de la persona
    fun setSexoPersona(sexo: String) {
        _sexoPersona = sexo
    }
}