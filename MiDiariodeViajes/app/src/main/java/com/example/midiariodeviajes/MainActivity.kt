package com.example.midiariodeviajes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.midiariodeviajes.domain.Reposirories.DestinoRepository.destinos
import com.example.midiariodeviajes.presentation.View.DestinationDetails
import com.example.midiariodeviajes.presentation.View.DestinationList
import com.example.midiariodeviajes.presentation.ViewModels.DestinoViewModel
import com.example.midiariodeviajes.ui.theme.MiDiarioDeViajesTheme

class MainActivity : ComponentActivity() {
    //DECLARAR VIEWMODEL
    private val viewmodel: DestinoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiDiarioDeViajesTheme {
                // El NavController y NavHost deben estar dentro del tema
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "DestinoView.kt"
                ) {
                    composable("DestinoView.kt") {
                        DestinationList(
                            modifier = Modifier.fillMaxSize(), viewmodel,
                            navController
                        )
                    }
                    composable("DestinoView/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")
                        DestinationDetails(destinationId = id)
                    }

                }
            }
        }
    }
}





