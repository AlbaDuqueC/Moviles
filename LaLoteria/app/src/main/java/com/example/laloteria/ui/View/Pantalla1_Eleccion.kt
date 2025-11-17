package com.example.laloteria.ui.View

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.laloteria.domain.entities.Apuesta
import com.example.laloteria.ui.theme.LaLoteriaTheme
import com.example.laloteria.ui.ViewModel.VMApuesta
@Composable
fun Eleccion(
    modifier: Modifier = Modifier,
    destinationViewModel: VMApuesta,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        for (num in 1..10) {
            boton(num, navController)
        }
    }
}

@Composable
fun boton(
    num: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            navController.navigate("Pantalla2_Apuesta/${num}")
        },
        modifier = modifier
    ) {
        Text(num.toString())
    }
}
