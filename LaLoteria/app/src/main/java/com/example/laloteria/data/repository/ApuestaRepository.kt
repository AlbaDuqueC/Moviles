package com.example.laloteria.data.repository

object ApuestaRepository {

    private var _saldo= 10

    fun getSaldo(): Int {
        return _saldo
    }

    fun setSaldo(saldo: Int){
        _saldo=saldo
    }

}