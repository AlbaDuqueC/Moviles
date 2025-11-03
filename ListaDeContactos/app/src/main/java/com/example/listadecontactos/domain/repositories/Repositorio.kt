package com.example.listadecontactos.domain.repositories

import com.example.listadecontactos.data.entities.Contacto

object Repositorio {

    val listaContactos = mutableListOf(

        Contacto(1, "Juan Pérez", "123456789", "Masculino"),
        Contacto(2, "María López", "987654321", "Femenino"),
        Contacto(3, "Carlos Gómez", "555123456", "Masculino"),
        Contacto(4, "Ana Torres", "444987654", "Femenino"),
        Contacto(5, "Luis Ramírez", "333456789", "Masculino"),
        Contacto(6, "Sofía Martínez", "222654987", "Femenino"),
        Contacto(7, "Diego Fernández", "111789456", "Masculino"),
        Contacto(8, "Laura Castillo", "666321987", "Femenino"),
        Contacto(9, "Andrés Morales", "777654123", "Masculino"),
        Contacto(10, "Valentina Herrera", "888987321", "Femenino"),


        )

    fun getAllContacts(): List<Contacto> {
        return listaContactos
    }

    fun addContacts(nombre:String, telefono:String, genero: String){

        var id= listaContactos.last().id +  1

        var newContact= Contacto(id, nombre, telefono, genero)

        listaContactos.add(newContact)

    }

}

