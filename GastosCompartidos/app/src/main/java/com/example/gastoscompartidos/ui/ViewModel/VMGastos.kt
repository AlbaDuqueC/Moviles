package com.example.gastoscompartidos.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.example.gastoscompartidos.data.repository.GastosRepository

class VMGastos : ViewModel() {

    fun CalcularDinero () : Double{

        val dinero : Double = (GastosRepository.getDinero() / GastosRepository.getPersona()).toDouble()

        return dinero;

    }


    fun IntroducirDatos (numPersonas: Int, dineroTotal: Int){

        GastosRepository.setDinero(dineroTotal)

        GastosRepository.setPersona(numPersonas)


    }

    fun getListadoPersonas(): List<String>{
        return GastosRepository.getNombres()
    }

    fun setListadoPersonas(listPersonas: List<String>){
        GastosRepository.setNombres(listPersonas)
    }

}