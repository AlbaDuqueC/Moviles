package com.example.ejemplovm.data

import com.example.ejemplovm.domain.User

object UserRepository {

    val users = mutableListOf<User>(User(1, "Ana"), User(2, "Luis"))
    fun getUsers(): List<User> {
        return users.toList()
    }

    fun insert(user: User) {
        users.add(user)
    }


}