package com.example.navejemplo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navejemplo.ui.presentation.FormularioPersona
import com.example.navejemplo.ui.presentation.PersonaRow
import com.example.navejemplo.ui.theme.NavEjemploTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavEjemploTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "VFormulario"
                ) {
                    composable("VFormulario") {
                        FormularioPersona(
                            onAddContact = {
                                navController.navigate("VMostrarLista")
                            }
                        )
                    }
                    /*composable("VMostrarLista") {
                        PersonaRow(
                            onAddContact = {
                                navController.navigate("VFormulario")
                            }
                        )
                    }*/
                }
            }
        }
    }
}