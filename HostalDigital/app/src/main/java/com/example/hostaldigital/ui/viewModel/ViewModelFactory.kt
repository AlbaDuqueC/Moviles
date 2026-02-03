package com.example.hostaldigital.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hostaldigital.data.database.HostalDatabase
import com.example.hostaldigital.data.repository.BookingRepository
import com.example.hostaldigital.data.repository.RoomRepository
import com.example.hostaldigital.data.repository.UserRepository


class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private val database by lazy { HostalDatabase.getDatabase(context) }

    private val userRepository by lazy { UserRepository(database.userDao()) }
    private val roomRepository by lazy { RoomRepository(database.roomDao()) }

    // Corregido: El BookingRepository ahora solo recibe el DAO (según el error anterior)
    private val bookingRepository by lazy {
        BookingRepository(database.bookingDao())
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(RoomListViewModel::class.java) -> {
                RoomListViewModel(roomRepository) as T
            }
            modelClass.isAssignableFrom(BookingViewModel::class.java) -> {
                // Ajustado según la estructura común de tus ViewModels
                BookingViewModel(
                    bookingRepository,
                    roomRepository = roomRepository
                ) as T
            }
            modelClass.isAssignableFrom(OwnerViewModel::class.java) -> {
                // Ajustado para que coincida con lo que el Repositorio ofrece ahora
                OwnerViewModel(roomRepository, bookingRepository, userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}