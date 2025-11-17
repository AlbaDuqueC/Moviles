package com.example.laloteria.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.laloteria.data.repository.ApuestaRepository

class VMApuesta : ViewModel(){

    private val _apuestaRepository = ApuestaRepository

    private var _saldo= _apuestaRepository.getSaldo()

    fun getSaldo() : Int{
        return _saldo
    }

    fun ganar(dinero : Int){

        _saldo += (dinero*2)
        _apuestaRepository.setSaldo(_saldo)
    }

    fun perder(dinero: Int){

        _saldo-= dinero
        _apuestaRepository.setSaldo(_saldo)
    }

}