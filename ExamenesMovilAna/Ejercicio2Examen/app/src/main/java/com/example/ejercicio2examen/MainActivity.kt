package com.example.ejercicio2examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio2examen.ui.theme.Ejercicio2ExamenTheme
import com.example.ejercicio2examen.ui.viewmodels.VMPersonasImporte
import com.example.ejercicio2examen.ui.views.ListaPersonasGrupo
import com.example.ejercicio2examen.ui.views.NombresIntegrantes
import com.example.ejercicio2examen.ui.views.PersonasPorGrupoTotalPagar
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewmodel: VMPersonasImporte by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio2ExamenTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    //variable para usar el navController
                    navController = navController,
                    //primer destino al iniciar la aplicacion
                    startDestination = "V1PersonaPorGrupoTotalPagar"
                ) {
                    //uno de los destinos de la aplicación
                    composable("V1PersonaPorGrupoTotalPagar") {
                        PersonasPorGrupoTotalPagar(
                            navController, viewmodel
                        )
                    }
                    composable("V2NombresIntegrantes/{numeroPersonas}") { backStackEntry ->
                        val numeroPersonas = backStackEntry.arguments?.getString("numeroPersonas")
                        //comprueba si el numero de personas del grupo es null o no
                        if (numeroPersonas != null) {
                            NombresIntegrantes(
                                cantidadIntegrantes = numeroPersonas.toInt(),
                                navController = navController,
                                viewmodel
                            )
                        }
                    }
                    composable("V3Resumen") {
                        ListaPersonasGrupo(
                            navController = navController, viewmodel
                        )
                    }
                }
            }
        }
    }
}
