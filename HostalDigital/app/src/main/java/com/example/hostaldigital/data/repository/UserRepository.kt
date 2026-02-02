package com.example.hostaldigital.data.repository


import com.example.hostaldigital.domain.dao.UserDao
import com.example.hostaldigital.domain.entities.UserEntity
import com.example.hostaldigital.ui.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) {

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)?.let {
            User(id = it.id, username = it.username, isOwner = it.isOwner)
        }
    }

    suspend fun register(username: String, password: String): Result<User> {
        return try {
            // Verificar si el usuario ya existe
            val existingUser = userDao.getUserByUsername(username)
            if (existingUser != null) {
                Result.failure(Exception("El usuario ya existe"))
            } else {
                val userId = userDao.insert(
                    UserEntity(username = username, password = password, isOwner = false)
                )
                val user = User(id = userId.toInt(), username = username, isOwner = false)
                Result.success(user)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)?.let {
            User(id = it.id, username = it.username, isOwner = it.isOwner)
        }
    }

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { User(id = it.id, username = it.username, isOwner = it.isOwner) }
        }
    }
}