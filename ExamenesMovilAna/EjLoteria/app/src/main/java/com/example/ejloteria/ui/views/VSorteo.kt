package com.example.ejloteria.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejloteria.ui.viewmodels.VMApuesta
import kotlin.random.Random

@Composable
fun Sorteo(
    cantidadApostada: Int,
    numeroApostado: Int,
    navController: NavHostController,
    apuestaVM: VMApuesta = viewModel()
) {

    var apuesta: Int = cantidadApostada
    val numeroAleatorioGanador: Int = numeroGanador()
    //obtiene el contexto actual
    val context = LocalContext.current
    var ganador: Boolean = false

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row() {

            //anuncia el numero ganador
            Text("${numeroAleatorioGanador}", fontSize = 30.sp)

            //si ha acertado
            if (numeroAleatorioGanador == numeroApostado) {
                ganador = true
                Toast.makeText(context, "¡Felicidades, has ganado!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Ohhh... Has perdido...", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        Row() {
            Button(
                onClick = {
                    //vuelve a la pantalla 1
                    navController.navigate("VBotonesNumeros")
                    if (ganador) {

                    } else {
                        //se actualiza
                        apuestaVM.restaDeSaldo(apuesta)
                    }
                }
            ) {
                Text("Jugar de nuevo")
            }
        }
        Row() {
            Button(
                onClick = {
                    //reinicio el saldo
                    apuestaVM.reiniciarSaldo()
                    //vuelve a la pantalla 1
                    navController.navigate("VBotonesNumeros")
                }
            ) {
                Text("Salir")
            }
        }
    }

}

//funcion que selecciona un numero aleatorio ganador
@Composable
fun numeroGanador(): Int {
    //almacenará el numero aleatorio ganador
    var numeroAleatorio by remember { mutableStateOf(0) }
    //genera un numero aleatorio y lo almacena el la variable
    numeroAleatorio = Random.nextInt(1, 10)

    return numeroAleatorio
}