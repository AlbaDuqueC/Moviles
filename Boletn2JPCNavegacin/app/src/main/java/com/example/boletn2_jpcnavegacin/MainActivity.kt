package com.example.boletn2_jpcnavegacin

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
import com.example.boletn2_jpcnavegacin.ui.theme.Boletín2JPCNavegaciónTheme
import com.example.boletn2_jpcnavegacin.ui.viewmodel.VMDestination
import com.example.boletn2_jpcnavegacin.ui.views.VDestinationList

class MainActivity : ComponentActivity() {
    //DECLARAR VIEWMODEL
    private val viewmodel: VMDestination by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletín2JPCNavegaciónTheme {
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
