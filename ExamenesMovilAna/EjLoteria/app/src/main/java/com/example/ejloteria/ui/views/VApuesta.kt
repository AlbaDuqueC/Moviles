package com.example.ejloteria.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejloteria.ui.viewmodels.VMApuesta

@Composable
fun DatosJugador(
    apuestaNumero: Int,
    navController: NavHostController,
    apuestaVM: VMApuesta = viewModel()
) {
    //cantidad que quiere apostar el usuario
    var cantidadApostada: Int = 0
    //obtiene el contexto actual
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row() {
            Text("Saldo actual: ${apuestaVM.saldoJugador()}")
        }
        Row() {
            Text("Apuesta actual: ${apuestaNumero}")
        }
        Row() {
            //se iguala el resultado de la apuesta a la variable
            cantidadApostada = InputApuesta()
        }
        Row() {
            Button(
                onClick = {
                    //si la cantidad apostada es mayor al saldo
                    if (cantidadApostada > apuestaVM.saldoJugador()) {
                        Toast.makeText(context, "Cantidad apostada incorrecta", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        navController.navigate("VSorteo/${cantidadApostada}/${apuestaNumero}")
                    }
                }
            ) {
                Text("Apostar")
            }
        }
    }
}

@Composable
fun InputApuesta(): Int {

    //variable que almacenará el texto cambiante
    var numApuesta by remember { mutableIntStateOf(0) }

    TextField(
        value = numApuesta.toString(),
        onValueChange = { numApuesta = it.toInt() },
        label = { Text("Introduzca su apuesta: ") },
        modifier = Modifier.padding(8.dp)
    )

    Text("Apuesta: ${numApuesta}")
    return numApuesta
}