package com.example.ejloteria.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ejloteria.data.repositories.RepositoryApuesta

class VMApuesta : ViewModel() {

    //variable que se igualará al repositorio para poder usar sus metodos
    private val _apuestaRepository = RepositoryApuesta

    //variable que almacena el saldo actual del jugador
    private var _saldoActualJugador = _apuestaRepository.getSaldo()
    //almacena el saldo inicial
    private val _saldoInicial = _apuestaRepository.getSaldo()

    fun saldoJugador(): Int {
        return _saldoActualJugador
    }

    //actualiza el saldo del jugador
    fun sumaDeSaldo(importe: Int) {
        _saldoActualJugador += importe
        _apuestaRepository.setSaldo(_saldoActualJugador)
    }

    //actualiza el saldo del jugador
    fun restaDeSaldo(importe: Int) {
        _saldoActualJugador -= importe
        _apuestaRepository.setSaldo(_saldoActualJugador)
    }

    //reinicia el saldo
    fun reiniciarSaldo() {
        _apuestaRepository.setSaldo(_saldoInicial)
    }
}