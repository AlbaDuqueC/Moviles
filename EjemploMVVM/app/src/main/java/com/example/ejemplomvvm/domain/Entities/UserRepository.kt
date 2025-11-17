package com.example.ejemplomvvm.domain.Entities

object UserRepository {

    val users = mutableListOf<User>(User(1, "Ana"), User(2, "Luis"));



    fun getUsers(): List<User> {
        return users.toList()
    }
    fun insert(user:User) { users.add(user) }


}