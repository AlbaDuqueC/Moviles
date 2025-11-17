package com.example.laloteria.domain.entities

data class Apuesta(

    val numero: Int,
    val dineroApostado: Int,
    val saldo: Int =10

)
