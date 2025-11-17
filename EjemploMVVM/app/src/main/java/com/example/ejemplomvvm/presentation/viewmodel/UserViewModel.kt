package com.example.ejemplomvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.ejemplomvvm.domain.Entities.User
import com.example.ejemplomvvm.domain.Entities.UserRepository
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class UserViewModel {

    private val _repo = UserRepository
    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> get() = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = _repo.getUsers()
    }
    fun insertUser(user:User){
        _repo.insert(user)
        loadUsers()
    }

}