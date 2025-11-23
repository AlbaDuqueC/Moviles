package com.example.ejercicio2examen.data.repositories

import com.example.ejercicio2examen.domain.entities.Persona

object RepositoryPersonas {

    //variable que almacenará la cantidad a pagar entre TODOS los integrantes
    private var _cantidadPagarTotal: Int = 0

    //variable privada que almacenará una lista mutable de personas
    private var _listaPersonas = mutableListOf<Persona>()

    //función get que devolverá una lista mutable de todas las personas
    fun getAllPersonas(): MutableList<Persona>{
        return _listaPersonas
    }

    //función get para coger la cantidad total a pagar
    fun getCantidadTotalPagar(): Int {
        return _cantidadPagarTotal
    }

    //función set para actualizar la catidad total a pagar
    fun setCantidadTotalPagar(cantidadAPagar: Int) {
        _cantidadPagarTotal = cantidadAPagar
    }

    //funcion para insertar un nuevo integrante del grupo a la lista
    fun insertarIntegrante(personaNueva: Persona) {
        _listaPersonas.add(personaNueva)
    }

}