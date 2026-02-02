package com.example.hostaldigital.ui.model

// model del usuario
data class User(
    // id del usuario
    val id: Int,

    // nombre del usuario
    val username: String,

    val isOwner: Boolean
)