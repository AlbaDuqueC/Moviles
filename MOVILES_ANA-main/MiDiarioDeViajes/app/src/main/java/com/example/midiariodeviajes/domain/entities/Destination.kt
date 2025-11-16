package com.example.midiariodeviajes.domain.entities

///Clase que representa un destino
data class Destination(
    val id: Int,
    val nombre: String,
    val pais: String,
    val descripcion: String
)
