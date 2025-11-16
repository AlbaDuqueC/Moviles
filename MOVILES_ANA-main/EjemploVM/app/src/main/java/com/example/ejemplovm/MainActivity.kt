package com.example.ejemplovm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ejemplovm.ui.theme.EjemploVMTheme
import com.example.ejemplovm.ui.viewmodels.UserViewModel
import com.example.ejemplovm.ui.views.UserListScreen

class MainActivity : ComponentActivity() {
    // ViewModel compartido para toda la actividad
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EjemploVMTheme {
                UserListScreen(userViewModel = userViewModel)
            }
        }
    }
}

