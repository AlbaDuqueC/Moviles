package com.example.gastoscompartidos.ui.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gastoscompartidos.ui.ViewModel.VMGastos

//Aquí se solicitarán los nombres de los componentes del grupo según el número indicado en la pantalla anterior.
//Un botón “Continuar” pasará a la siguiente pantalla.

@Composable
fun Nombres(
    personas: Int,
    modifier: Modifier= Modifier,
    destinationViewModel: VMGastos,
    navController: NavHostController
){
    var nombres by remember { mutableStateOf<List<String>>(emptyList()) }
    var continuarVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(20.dp)) {

        Button(
            onClick = {
                val n = personas
                if (n > 0) {
                    nombres = List(n) { "" }
                    continuarVisible = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generar cajas para nombres")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Cajas para los nombres
        nombres.forEachIndexed { index, nombre ->
            OutlinedTextField(
                value = nombre,
                onValueChange = { nuevo ->
                    nombres = nombres.toMutableList().also { it[index] = nuevo }
                },
                label = { Text("Nombre ${index + 1}") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
            )
        }

        Boton(nombres, destinationViewModel, navController, modifier)



    }

}
@Composable
fun Boton(
    list: List<String>,
    destinationViewModel: VMGastos,
    navController: NavController,
    modifier: Modifier = Modifier

){

    Button(
        onClick = {

            destinationViewModel.setListadoPersonas(list)
            navController.navigate("Pantalla3_Resumen")
        }
    ) {
        Text("Continuar")
    }


}