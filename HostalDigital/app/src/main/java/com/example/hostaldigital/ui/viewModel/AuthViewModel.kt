package com.example.hostaldigital.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostaldigital.data.repository.UserRepository
import com.example.hostaldigital.ui.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val user = userRepository.login(username, password)
                if (user != null) {
                    _currentUser.value = user
                    _authState.value = AuthState.Success
                } else {
                    _authState.value = AuthState.Error("Usuario o contraseña incorrectos")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun register(username: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            // Validaciones
            if (username.isBlank()) {
                _authState.value = AuthState.Error("El nombre de usuario no puede estar vacío")
                return@launch
            }

            if (password.isBlank()) {
                _authState.value = AuthState.Error("La contraseña no puede estar vacía")
                return@launch
            }

            if (password != confirmPassword) {
                _authState.value = AuthState.Error("Las contraseñas no coinciden")
                return@launch
            }

            if (password.length < 6) {
                _authState.value = AuthState.Error("La contraseña debe tener al menos 6 caracteres")
                return@launch
            }

            try {
                val result = userRepository.register(username, password)
                result.fold(
                    onSuccess = { user ->
                        _currentUser.value = user
                        _authState.value = AuthState.Success
                    },
                    onFailure = { error ->
                        _authState.value = AuthState.Error(error.message ?: "Error al registrar")
                    }
                )
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun logout() {
        _currentUser.value = null
        _authState.value = AuthState.Idle
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }

    fun isLoggedIn(): Boolean = _currentUser.value != null

    fun isOwner(): Boolean = _currentUser.value?.isOwner == true
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}