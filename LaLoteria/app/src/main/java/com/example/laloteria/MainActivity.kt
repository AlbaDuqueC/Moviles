package com.example.laloteria

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
import com.example.laloteria.ui.View.Apostar
import com.example.laloteria.ui.View.Eleccion
import com.example.laloteria.ui.View.Resultado
import com.example.laloteria.ui.ViewModel.VMApuesta
import com.example.laloteria.ui.theme.LaLoteriaTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewmodel: VMApuesta by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            LaLoteriaTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "Pantalla1_Eleccion"
                ) {
                    composable("Pantalla1_Eleccion") {
                        Eleccion(
                            modifier = Modifier.fillMaxSize(),
                            viewmodel,
                            navController
                        )
                    }

                    composable("Pantalla2_Apuesta/{numero}") {

                            backStackEntry ->
                        val num = backStackEntry.arguments?.getString("numero")

                        if (num!=null) {
                            Apostar(
                                modifier = Modifier.fillMaxSize(), viewmodel,
                                navController, numero = num.toInt()
                            )
                        }
                    }

                    composable("Pantalla3_Resultado/{numero}/{apuesta}") {
                            backStackEntry ->
                        val num = backStackEntry.arguments?.getString("numero")
                        val apu= backStackEntry.arguments?.getString("apuesta")

                        if (num!=null && apu!=null) {
                            Resultado(
                                modifier = Modifier.fillMaxSize(), viewmodel,
                                navController, numero = num.toInt(), apuesta = apu.toInt()
                            )
                        }
                    }

                }
            }


        }
    }
}
