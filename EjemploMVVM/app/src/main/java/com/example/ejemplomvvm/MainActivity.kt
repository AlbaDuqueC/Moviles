package com.example.ejemplomvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ejemplomvvm.presentation.view.UserListScreen
import com.example.ejemplomvvm.ui.theme.EjemploMVVMTheme
import kotlin.getValue
import com.example.ejemplomvvm.presentation.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    // ViewModel compartido para toda la actividad
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EjemploMVVMTheme {
                UserListScreen(userViewModel=userViewModel)

            }
        }
    }
}
