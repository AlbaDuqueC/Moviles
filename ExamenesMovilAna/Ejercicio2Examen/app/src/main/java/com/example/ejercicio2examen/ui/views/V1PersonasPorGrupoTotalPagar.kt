package com.example.ejercicio2examen.ui.views

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejercicio2examen.ui.viewmodels.VMPersonasImporte

@Composable
fun PersonasPorGrupoTotalPagar(
    navController: NavHostController, viewmodel: VMPersonasImporte
) {
    //variables que almacenarán lo que el usuario introduzca por los input
    var numIntegrantes by remember { mutableIntStateOf(0) }
    var cantidadPagar by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            input(
                textoPlaceholder = "Número de personas en el grupo",
                value = numIntegrantes,
                onValueChange = { numIntegrantes = it }
            )
        }
        Row(modifier = Modifier.padding(10.dp)) {
            input(
                textoPlaceholder = "Cantidad a pagar",
                value = cantidadPagar,
                onValueChange = { cantidadPagar = it }
            )
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Text("Su grupo tiene $numIntegrantes")
        }

        Row(modifier = Modifier.padding(10.dp)) {
            Text("La cantidad a dividir de su grupo es $cantidadPagar")
        }
        Row(modifier = Modifier.padding(10.dp)) {
            //botón para navegar a la siguiente pantalla mandando los datos especificados
            BotonSiguienteVista(numIntegrantes, cantidadPagar, navController, viewmodel)
        }
    }
}

@Composable
private fun input(textoPlaceholder: String, value: Int, onValueChange: (Int) -> Unit) {
    TextField(
        value = value.toString(),
        onValueChange = {
            val number = it.toIntOrNull() ?: 0
            onValueChange(number)
        },
        label = { Text(textoPlaceholder) },
        modifier = Modifier.padding(8.dp)
    )
}

//composable que crearia un boton que mandaría el nº de integrantes del grupo
//y la cantidad especificada a repartir a la siguiente pantalla
@Composable
private fun BotonSiguienteVista(
    numeroIntegrantes: Int,
    cantidadDividir: Int,
    navController: NavHostController,
    personasVM: VMPersonasImporte
) {
    Button(
        //en el onclick navegará a la pantalla 2 donde deberá indicar una cantidad
        onClick = {
            //al hacer click navega hacia la vista siguiente mandándole el nº de
            //integrantes que tiene el grupo junto con la cantidad a dividir entre estos
            navController.navigate("V2NombresIntegrantes/${numeroIntegrantes}")
            //guardo la cantidad a dividir
            personasVM.setCantidadAPagar(cantidadDividir)
        }
    )
    { Text("Enviar") }
}