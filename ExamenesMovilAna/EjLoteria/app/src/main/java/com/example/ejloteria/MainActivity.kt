package com.example.ejloteria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejloteria.ui.theme.EjLoteriaTheme
import com.example.ejloteria.ui.viewmodels.VMApuesta
import com.example.ejloteria.ui.views.BotonesNumeros
import com.example.ejloteria.ui.views.DatosJugador
import com.example.ejloteria.ui.views.Sorteo

class MainActivity : ComponentActivity() {
    private val viewmodel: VMApuesta by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjLoteriaTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    //variable para usar el navController
                    navController = navController,
                    //primer destino al iniciar la aplicacion
                    startDestination = "VBotonesNumeros"
                ) {
                    //uno de los destinos de la aplicación
                    composable("VBotonesNumeros") {
                        BotonesNumeros(
                            modifier = Modifier.fillMaxSize(),
                            navController
                        )
                    }
                    composable("VApuesta/{numero}") { backStackEntry ->
                        val numero = backStackEntry.arguments?.getString("numero")
                        if (numero != null) {
                            DatosJugador(
                                apuestaNumero = numero.toInt(),
                                navController = navController
                            )
                        }
                    }
                    composable("VSorteo/{cantidadApostada}/{numeroApostado}") { backStackEntry ->
                        val cantidadApostada = backStackEntry.arguments?.getString("cantidadApostada")
                        val numeroApostado = backStackEntry.arguments?.getString("numeroApostado")
                        if (cantidadApostada != null && numeroApostado != null) {
                            Sorteo(cantidadApostada = cantidadApostada.toInt(),
                                numeroApostado = numeroApostado.toInt(),
                                navController = navController)
                        }
                    }
                }
            }
        }
    }
}

