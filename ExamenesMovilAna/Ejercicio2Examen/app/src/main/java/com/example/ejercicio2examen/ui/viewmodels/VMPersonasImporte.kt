package com.example.ejercicio2examen.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ejercicio2examen.data.repositories.RepositoryPersonas
import com.example.ejercicio2examen.domain.entities.Persona

class VMPersonasImporte : ViewModel() {

    //variable igualada al repositorio para poder usar sus métodos
    private val _repositoryPersonas = RepositoryPersonas

    //variable que almacenará una lista de las personas integrantes del grupo
    private val _personas = mutableStateOf<List<Persona>>(emptyList())

    //variable pública que se le muestra a la vista
    val personas: State<List<Persona>> get() = _personas

    //constructor del vm que siempre inicializará el objeto con una
    //lista de personas, al principio vacía
    init {
        loadPersonas()
    }

    //función que agarra a todas las personas que se han introducido y
    //las iguala al valor de la lista de las personas
    private fun loadPersonas() {
        _personas.value = _repositoryPersonas.getAllPersonas()
    }

    //funcion para poder coger la cantidad total a pagar
    fun getCantidadPagar(): Int {
        return _repositoryPersonas.getCantidadTotalPagar()
    }

    //función para actualizar la cantidad total a pagar entre los
    //integrantes del grupo
    fun setCantidadAPagar(cantidadAPagar: Int) {
        _repositoryPersonas.setCantidadTotalPagar(cantidadAPagar)
    }

    //función para poder insertar un nuevo integrante a la lista del grupo
    fun insertarIntegrante(personaNueva: Persona) {
        _repositoryPersonas.insertarIntegrante(personaNueva)
        loadPersonas()
    }

}