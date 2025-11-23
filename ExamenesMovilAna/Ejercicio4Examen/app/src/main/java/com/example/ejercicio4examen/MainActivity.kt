package com.example.ejercicio4examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio4examen.ui.theme.Ejercicio4ExamenTheme
import com.example.ejercicio4examen.ui.viewmodels.VMProductos
import com.example.ejercicio4examen.ui.views.ComprarProducto
import com.example.ejercicio4examen.ui.views.ListaProductosElegir
import com.example.ejercicio4examen.ui.views.VenderProducto
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewmodel: VMProductos by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio4ExamenTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    //variable para usar el navController
                    navController = navController,
                    //primer destino al iniciar la aplicacion
                    startDestination = "V1ElegirProducto"
                ) {
                    //uno de los destinos de la aplicación
                    composable("V1ElegirProducto") {
                        ListaProductosElegir(
                            navController, viewmodel
                        )
                    }
                    composable("V2Comprar/{nombreProducto}") { backStackEntry ->
                        val nombreProducto =
                            backStackEntry.arguments?.getString("nombreProducto")
                        // Llama a la función ComprarProducto con el nombre del producto
                        ComprarProducto(
                            navController = navController,
                            viewmodel,
                            nombreProducto
                        )

                    }
                    composable("V3Vender") {
                        VenderProducto(
                            navController = navController,
                            viewmodel
                        )
                    }
                }
            }
        }
    }
}
