package com.example.ejemplovm.ui.viewmodels

import com.example.ejemplovm.data.UserRepository
import com.example.ejemplovm.domain.User
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class UserViewModel {

    private val _repo = UserRepository
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() =  _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = _repo.getUsers()
    }

    fun insertUser(user: User) {
        _repo.insert(user)
        loadUsers()
    }


}