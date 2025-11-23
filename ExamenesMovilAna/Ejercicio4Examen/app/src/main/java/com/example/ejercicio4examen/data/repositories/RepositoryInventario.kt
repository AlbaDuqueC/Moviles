package com.example.ejercicio4examen.data.repositories

import com.example.ejercicio4examen.domain.entities.Producto

object RepositoryInventario {

    //variable que almacena los creditos del usuario
    private var _creditosUsuario: Double = 10.0

    //almacena una lista de los productos DEL usuario
    private var _listaProductosInventario = mutableListOf<Producto>()

    //función para poder obtener todos los productos que hay en el
    //inventario del usuario
    fun getAllListaProductosInventario(): MutableList<Producto> {
        return _listaProductosInventario
    }

    //funcion para agregar un nuevo producto a la lista
    fun insertarProductoInventario(producto: Producto) {
        _listaProductosInventario.add(producto)
    }

    //funcion para eliminar un elemento de la lista
    fun eliminarProductoInventario(producto: Producto) {
        _listaProductosInventario.remove(producto)
    }

    //función para obtener los créditos actuales del usuario
    fun getCreditosUsuario(): Double {
        return _creditosUsuario
    }

    //funcion para actualizar los creditos del usuario
    fun setCreditosUsuario(creditos: Double) {
        _creditosUsuario = creditos
    }

}