package com.example.hostaldigital.ui.model

// model del usuario
data class User(
    // id del usuario
    val id: Int,

    // nombre del usuario
    val name: String,

    val email: String,

    val password: String,

    val isOwner: Boolean
)