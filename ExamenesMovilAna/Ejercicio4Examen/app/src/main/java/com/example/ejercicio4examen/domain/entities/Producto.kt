package com.example.ejercicio4examen.domain.entities

data class Producto(
    var id: Int,
    val nombre: String,
    val descripcion: String,
    var precio: Double = 2.0
)
