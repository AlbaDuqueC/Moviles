package com.example.pesoideal.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pesoideal.ui.viewModel.VMPesoIDeal

//En esta pantalla se solicitará el nombre, el peso y se elegirá
// la opción de sexo mediante dos botones colocados de forma
// adecuada. Cualquiera de ellos pasará a la siguiente pantalla.

@Composable
fun Sexo(

    modifier: Modifier,
    VMPeso: VMPesoIDeal = viewModel(),
    navController: NavHostController

){

    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Introduce tus datos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Peso (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Selecciona tu sexo:")

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {

                    VMPeso.setNombre(nombre)
                    VMPeso.setPeso(peso.toDouble())
                    VMPeso.setSexo("Hombre")

                    navController.navigate("Pantalla2")

                }
            ) {
                Text("Hombre")
            }

            Button(
                onClick = {
                    VMPeso.setNombre(nombre)
                    VMPeso.setPeso(peso.toDouble())
                    VMPeso.setSexo("Mujer")

                    navController.navigate("Pantalla2")
                }
            ) {
                Text("Mujer")
            }
        }
    }


}