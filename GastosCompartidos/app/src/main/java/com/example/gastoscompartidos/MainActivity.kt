package com.example.gastoscompartidos

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
import com.example.gastoscompartidos.ui.View.Eleccion
import com.example.gastoscompartidos.ui.View.Nombres
import com.example.gastoscompartidos.ui.View.Resultado
import com.example.gastoscompartidos.ui.ViewModel.VMGastos
import com.example.gastoscompartidos.ui.theme.GastosCompartidosTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewmodel: VMGastos by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastosCompartidosTheme {

                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "Pantalla1_Numeros"
                ) {
                    composable("Pantalla1_Numeros") {
                        Eleccion(
                            modifier = Modifier.fillMaxSize(),
                            viewmodel,
                            navController
                        )
                    }

                    composable("Pantalla2_Nombres/{personas}") {

                            backStackEntry ->
                        val personas = backStackEntry.arguments?.getString("personas")

                        if (personas!=null) {
                            Nombres(
                                personas = personas.toInt(),
                                modifier = Modifier.fillMaxSize(),
                                viewmodel,
                                navController,
                            )
                        }
                    }

                    composable("Pantalla3_Resumen") {

                            Resultado(

                                modifier = Modifier.fillMaxSize(),
                                viewmodel,
                                navController,
                            )

                    }

                }
            }
        }
    }
}

