package com.example.gastoscompartidos.ui.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gastoscompartidos.ui.ViewModel.VMGastos

//En esta pantalla se mostrará el resumen del reparto por persona.
//Un botón “Volver” comenzará de nuevo eliminando los datos que se hubieran guardado.

@Composable
fun Resultado(
    modifier: Modifier = Modifier,
    destinationViewModel: VMGastos,
    navController: NavHostController
){

    val nombres = destinationViewModel.getListadoPersonas()
    val dinero = destinationViewModel.CalcularDinero()

    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            "Resumen del reparto",
            Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Lista del reparto por persona
        nombres.forEach { nombre ->
            Text(
                text = "$nombre recibe: $dinero",
                Modifier.padding(8.dp)
            )
        }

        Button(
            onClick = {
                navController.navigate("Pantalla1_Numeros") {
                    popUpTo("Pantalla1_Numeros") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }

    }
}

