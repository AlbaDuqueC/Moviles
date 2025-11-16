package com.example.midiariodeviajes

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
import com.example.midiariodeviajes.ui.theme.MiDiarioDeViajesTheme
import com.example.midiariodeviajes.ui.viewmodels.VMDestination
import com.example.midiariodeviajes.ui.views.DestinationDetails
import com.example.midiariodeviajes.ui.views.DestinationList

class MainActivity : ComponentActivity() {
    //DECLARAR VIEWMODEL
    private val viewmodel: VMDestination by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiDiarioDeViajesTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "VDestinationList"
                ) {
                    composable("VDestinationList") {
                        DestinationList(
                            modifier = Modifier.fillMaxSize(), viewmodel,
                            navController
                        )
                    }
                    composable("VDetailScreen/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        DestinationDetails(destinationId = id)
                    }

                }
            }
        }
    }
}
