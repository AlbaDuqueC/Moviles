package com.example.laloteria.ui.View

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.laloteria.ui.ViewModel.VMApuesta

@Composable
fun Apostar(
    modifier: Modifier = Modifier,
    destinationViewModel: VMApuesta = viewModel(),
    navController: NavHostController,
    numero: Int
){

    var apuesta by remember { mutableStateOf(1) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Saldo actual: ${destinationViewModel.getSaldo()}")
        Text("Número apostado: ${numero.toString()}")

        // Selector de número
        SelectorNumero { nuevoNumero ->
            apuesta = nuevoNumero
        }

        Button(onClick = {
            navController.navigate("Pantalla3_Resultado/$numero/$apuesta")
        }) {
            Text("Ver resultado")
        }
    }
}


@Composable
fun SelectorNumero(onNumeroSeleccionado: (Int) -> Unit) {
    var apuesta by remember { mutableStateOf(1) }

    AndroidView(
        factory = { context ->
            NumberPicker(context).apply {
                minValue = 1
                maxValue = 10
                value = apuesta

                setOnValueChangedListener { _, _, newVal ->
                    apuesta = newVal
                    onNumeroSeleccionado(newVal)
                }
            }
        },
        update = { picker ->
            picker.value = apuesta
        }
    )
}


