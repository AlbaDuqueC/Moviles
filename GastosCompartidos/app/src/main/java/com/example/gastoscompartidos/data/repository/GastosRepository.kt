package com.example.gastoscompartidos.data.repository

object GastosRepository {

    private var _dinero=0

    private var _persona=0

    private lateinit var nombres: List<String>

    fun getDinero(): Int {
        return _dinero
    }

    fun getPersona() : Int{
        return _persona
    }

    fun getNombres() : List<String>{
        return nombres
    }

    fun setDinero(dinero: Int){
        _dinero=dinero
    }

    fun setPersona(persona: Int){
        _persona=persona
    }

    fun setNombres(lista: List<String>){
        nombres= lista
    }
}