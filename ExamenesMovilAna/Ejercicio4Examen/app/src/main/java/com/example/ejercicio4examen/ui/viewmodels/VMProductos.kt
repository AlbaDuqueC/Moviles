package com.example.ejercicio4examen.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ejercicio4examen.data.repositories.RepositoryInventario
import com.example.ejercicio4examen.data.repositories.RepositoryProductos
import com.example.ejercicio4examen.domain.entities.Producto

class VMProductos : ViewModel() {

    //variable igualada a los repositorios
    private val _repositoryProductos = RepositoryProductos
    private val _repositoryInventario = RepositoryInventario

    //almaceno la lista de los productos
    private var _productos = mutableStateOf<List<Producto>>(emptyList())

    //variable que almacena todo lo que hay en la lista de los productos
    val productos: State<List<Producto>> get() = _productos

    //almaceno la lista de los productos
    private var _productosInventario = mutableStateOf<List<Producto>>(emptyList())

    //variable que almacena todo lo que hay en la lista de los productos
    val productosInventario: State<List<Producto>> get() = _productosInventario

    //constructor
    init {
        loadProductos()
    }

    //se cargan los productos
    fun loadProductos() {
        _productos.value = _repositoryProductos.getAllListaProductos()
    }

    //funcion para poder obtener todos los productos
    fun getAllProductos(): MutableList<Producto> {
        return _repositoryProductos.getAllListaProductos()
    }

    //función para poder obtener todos los productos del INVENTARIO
    fun getAllProductosInventario(): MutableList<Producto> {
        return _repositoryInventario.getAllListaProductosInventario()
    }

    //funcion para poder insertar el producto en el inventario
    fun insertarProductoInventario(producto: Producto) {
        _repositoryInventario.insertarProductoInventario(producto)
    }

    //eliminar un producto del inventario
    fun eliminarProductoInventario(producto: Producto) {
        _repositoryInventario.eliminarProductoInventario(producto)
    }

    //función para agarrar los créditos del usuario
    fun getCreditosUsuario(): Double {
        return _repositoryInventario.getCreditosUsuario()
    }

    //funcion para poder actualizar los creditos del usuario
    fun setCreditosUsuario(creditos: Double) {
        _repositoryInventario.setCreditosUsuario(creditos)
    }

}