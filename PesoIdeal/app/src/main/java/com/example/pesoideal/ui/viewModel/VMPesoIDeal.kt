package com.example.pesoideal.ui.viewModel

import androidx.lifecycle.ViewModel

class VMPesoIDeal : ViewModel() {

    private lateinit var _nombre: String

    private lateinit var _sexo: String

    private var _altura=0.0


    private var _peso=0.0

    fun getSexo(): String{

        return _sexo

    }

    fun setSexo(sexo : String){
        _sexo=sexo
    }

    fun getAltura(): Double {

        return _altura

    }

    fun setAltura(altura : Double){
        _altura=(altura/100)
    }

    fun getPeso(): Double {

        return _peso

    }

    fun setPeso(peso : Double){
        _peso=peso
    }

    fun setNombre(nombre: String){
        _nombre=nombre
    }

    fun getNombre():String{
        return _nombre
    }

    fun CalcularResultado(): Double{

        var coeficiente: Double

        var result: Double

        if(_sexo=="Mujer"){
            coeficiente=0.95
        }else{
            coeficiente=1.0
        }

        result=(_peso/(_altura*_altura))* coeficiente

        return result

    }




}