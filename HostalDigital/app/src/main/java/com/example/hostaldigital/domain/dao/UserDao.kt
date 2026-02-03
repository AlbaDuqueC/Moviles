package com.example.hostaldigital.domain.dao

import androidx.room.*
import com.example.hostaldigital.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // Da el usuario si coincide el nombre y la contraseña
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?

    // Da los usuarios con ese nombre
    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    // Inserta un usuario
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: UserEntity): Long

    // Da el usuario del id
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): UserEntity?

    // Da todos los usuarios
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?
}