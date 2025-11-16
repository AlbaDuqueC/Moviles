package com.example.listacontactos.data.repositories

import com.example.listacontactos.domain.entities.Contacto

object Repositorio {

    //lista de los contactos con los datos de la clase Contacto
    private val _listaContactos = listOf(
        Contacto(1, "Juan García", "611123456", "Masculino"),
        Contacto(2, "María López", "678456123", "Femenino"),
        Contacto(3, "Raúl Cimas", "644789456", "Masculino"),
        Contacto(4, "Ana Morantes", "693882147", "Femenino"),
        Contacto(5, "Luis Fernández", "622334455", "Masculino"),
        Contacto(6, "Carmen Ruiz", "655667788", "Femenino"),
        Contacto(7, "Pedro Sánchez", "699112233", "Masculino"),
        Contacto(8, "Laura Martínez", "688998877", "Femenino"),
        Contacto(9, "Sergio Ramos", "677445566", "Masculino"),
        Contacto(10, "Isabel Torres", "690223344", "Femenino")
    )


    //funcion que devuelve una lista de todos los contactos
    fun getAllListaContactos(): List<Contacto> {
        return _listaContactos
    }


}