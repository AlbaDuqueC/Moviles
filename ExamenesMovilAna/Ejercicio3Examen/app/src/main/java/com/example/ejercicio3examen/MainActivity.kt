package com.example.ejercicio3examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio3examen.ui.theme.Ejercicio3ExamenTheme
import com.example.ejercicio3examen.ui.viewmodels.VMPersonaPeso
import com.example.ejercicio3examen.ui.views.AsignarAltura
import com.example.ejercicio3examen.ui.views.IntroducirDatos
import com.example.ejercicio3examen.ui.views.PesoIdeal
import kotlin.getValue

class MainActivity : ComponentActivity() {
    //se instancia el viewmodel
    private val viewmodel: VMPersonaPeso by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio3ExamenTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    //variable para usar el navController
                    navController = navController,
                    //primer destino al iniciar la aplicacion
                    startDestination = "V1DatosPersona"
                ) {
                    //uno de los destinos de la aplicación
                    composable("V1DatosPersona") {
                        IntroducirDatos(
                            navController,
                            viewmodel
                        )
                    }
                    composable("V2Altura/{nombre}") { backStackEntry ->
                        val nombre = backStackEntry.arguments?.getString("nombre")
                        //comprueba si el numero de personas del grupo es null o no
                        AsignarAltura(
                            navController,
                            viewmodel,
                            nombre
                        )

                    }
                    composable("V3PesoIdeal/{numero}") { backStackEntry ->
                        val numero = backStackEntry.arguments?.getInt("numero")
                        if (numero != null) {
                            PesoIdeal(
                                navController,
                                viewmodel,
                                numero
                            )
                        }
                    }
                }
            }
        }
    }
}