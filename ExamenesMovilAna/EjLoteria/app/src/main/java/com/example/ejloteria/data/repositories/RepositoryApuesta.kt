package com.example.ejloteria.data.repositories

object RepositoryApuesta {

    //private val _apuesta : Apuesta? = null

    //saldo actual
    private var _saldoActual : Int = 10

    //función get para poder obtener el saldo del jugador
    fun getSaldo(): Int {
        return _saldoActual
    }

    //funcion set para poder actualizar el saldo del jugador
    fun setSaldo(importe: Int) {
        _saldoActual = importe
    }

}