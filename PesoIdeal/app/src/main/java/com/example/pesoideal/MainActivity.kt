package com.example.pesoideal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pesoideal.ui.theme.PesoIdealTheme
import com.example.pesoideal.ui.view.Altura
import com.example.pesoideal.ui.view.Resultado
import com.example.pesoideal.ui.view.Sexo
import com.example.pesoideal.ui.viewModel.VMPesoIDeal

class MainActivity : ComponentActivity() {

    private val viewModel: VMPesoIDeal by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PesoIdealTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "Pantalla1"
                ){
                    composable("Pantalla1"){
                        Sexo(
                            modifier=Modifier.fillMaxSize(),
                            viewModel,
                            navController
                        )
                    }

                    composable("Pantalla2"){

                        Altura(
                            modifier=Modifier.fillMaxSize(),
                            viewModel,
                            navController
                        )


                    }

                    composable("Pantalla3") {

                        Resultado(
                            modifier=Modifier.fillMaxSize(),
                            viewModel,
                            navController
                        )

                    }


                }

            }
        }
    }
}
