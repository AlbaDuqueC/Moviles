package com.example.ejercicio4examen.data.repositories

import com.example.ejercicio4examen.domain.entities.Producto

object RepositoryProductos {

    //lista con todos los productos actuales para vender
    private var _listaProductos = mutableListOf<Producto>(
        Producto(1, "Producto 1", "Descripción del producto 1"),
        Producto(2, "Producto 2", "Descripción del producto 2"),
        Producto(3, "Producto 3", "Descripción del producto 3")
    )

    //funcion para poder devolver una lista de todos los productos
    fun getAllListaProductos(): MutableList<Producto> {
        return _listaProductos
    }

}