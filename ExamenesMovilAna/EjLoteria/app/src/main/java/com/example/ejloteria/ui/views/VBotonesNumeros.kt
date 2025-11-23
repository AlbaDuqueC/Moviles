package com.example.ejloteria.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejloteria.ui.viewmodels.VMApuesta

@Composable
fun BotonesNumeros(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //for que va imprimiendo botones del uno al 9
        for (i in 1..9) {
            Row(modifier = Modifier.padding(10.dp)) {
                Boton(i, navController = navController)
            }
        }
    }
}

//funcion que crea un boton con un numero que le llega por parámetros
@Composable
fun Boton(numero: Int, navController: NavHostController) {
    Button(
        //en el onclick navegará a la pantalla 2 donde deberá indicar una cantidad
        onClick = {
            //al hacer click navega hacia la pantalla de apuesta con el numero seleccionado
            navController.navigate("VApuesta/${numero}")
        }
    )
    { Text(numero.toString()) }
}