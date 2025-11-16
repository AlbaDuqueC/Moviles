package com.example.navejemplo.data.repository

import com.example.navejemplo.domain.entities.Persona

object PersonaRepository {

    //lista vacía
    val listaPersonas = mutableListOf<Persona>()

    //funcion que devuelve el listado completo de las personas
    fun getAllPersonas(): List<Persona> {
        return listaPersonas
    }

}