package com.example.hostaldigital.ui.model

import com.example.hostaldigital.domain.entities.UserEntity

data class UserSession(
    val user: User? = null, // Cambiado de UserEntity a User
    val isLoggedIn: Boolean = false
) {
    val isOwner: Boolean
        get() = user?.isOwner ?: false
}