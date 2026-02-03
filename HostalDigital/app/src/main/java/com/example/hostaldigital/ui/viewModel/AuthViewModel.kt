package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.UserRepository
import com.example.hostaldigital.domain.entities.UserEntity
import com.example.hostaldigital.domain.entities.toUiModel
import com.example.hostaldigital.ui.model.UiState
import com.example.hostaldigital.ui.model.User
import com.example.hostaldigital.ui.model.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userSession = MutableStateFlow(UserSession())
    val userSession: StateFlow<UserSession> = _userSession.asStateFlow()

    private val _authState = MutableStateFlow<UiState<User>>(UiState.Idle)
    val authState: StateFlow<UiState<User>> = _authState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = UiState.Loading

            val result = userRepository.login(email, password)

            result.fold(
                { entity -> // Aquí recibes el UserEntity del repositorio
                    val userUi = entity.toUiModel() // Lo convertimos a User de UI

                    _userSession.value = UserSession(user = userUi, isLoggedIn = true)
                    _authState.value = UiState.Success(data = userUi)
                },
                { exception ->
                    _authState.value = UiState.Error(exception.message ?: "Error al iniciar sesión")
                }
            )
        }
    }


    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = UiState.Loading

            val user = UserEntity(

                name = name,
                email = email,
                password = password,
                isOwner = false
            )

            val result = userRepository.registerUser(user)

            result.fold(
                onSuccess = {
                    // Auto-login después del registro
                    login(email, password)
                },
                onFailure = { exception ->
                    _authState.value = UiState.Error(exception.message ?: "Error al registrarse")
                }
            )
        }
    }

    fun logout() {
        _userSession.value = UserSession()
        _authState.value = UiState.Idle
    }

    fun resetAuthState() {
        _authState.value = UiState.Idle
    }
}