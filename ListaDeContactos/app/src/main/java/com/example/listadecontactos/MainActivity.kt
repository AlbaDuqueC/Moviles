package com.example.listadecontactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.listadecontactos.data.database.ContactoDataBase
import com.example.listadecontactos.ui.presentation.ContactsScreen
import com.example.listadecontactos.ui.presentation.Formulario
import com.example.listadecontactos.ui.theme.ListaDeContactosTheme

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var database: ContactoDataBase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,
            ContactoDataBase::class.java,
            "contacto-db"
        ).build()

        enableEdgeToEdge()
        setContent {
            ListaDeContactosTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "pantalla_lista",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("pantalla_lista") {
                            ContactsScreen(navController = navController)
                        }

                        composable("pantalla_formulario") {
                            Formulario(navController = navController)
                        }
                    }
                }
            }
        }
    }
}