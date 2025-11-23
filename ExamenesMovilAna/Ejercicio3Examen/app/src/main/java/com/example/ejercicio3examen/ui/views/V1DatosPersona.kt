package com.example.ejercicio3examen.ui.views

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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejercicio3examen.ui.viewmodels.VMPersonaPeso

@Composable
fun IntroducirDatos(
    navController: NavHostController,
    viewmodel: VMPersonaPeso
) {

    //variable para almacenar el nombre del usuario
    var nombre by remember { mutableStateOf("") }
    //variable para almacenar el peso del usuario
    var peso: Double by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            //input para poder escribir el nombre del usuario
            inputNombre("Introduzca su nombre", nombre, onValueChange = { nombre = it })
        }
        Row(modifier = Modifier.padding(10.dp)) {
            //input para poder escribir el nombre del usuario
            inputPeso("Introduzca su peso", peso, onValueChange = { peso = it })
        }
        Row(modifier = Modifier.padding(10.dp)) {
            BotonSexo("Masculino", navController, viewmodel, nombre, peso)
            BotonSexo("Femenino", navController, viewmodel, nombre, peso)
        }
    }

}

@Composable
fun BotonSexo(
    textoBoton: String,
    navController: NavHostController,
    viewmodel: VMPersonaPeso,
    nombre: String,
    peso: Double){
    Button(
        onClick = {
            navController.navigate("V2Altura/${nombre}")
            viewmodel.setPesoPersona(peso)
            viewmodel.setSexoPersona(textoBoton)
        }
    ) { Text(textoBoton) }
}

@Composable
fun inputNombre(
    textoPlaceholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(textoPlaceholder) },
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun inputPeso(
    textoPlaceholder: String,
    value: Double,
    onValueChange: (Double) -> Unit
) {
    TextField(
        value = value.toString(),
        onValueChange = { onValueChange(it.toDouble()) },
        label = { Text(textoPlaceholder) },
        modifier = Modifier.padding(8.dp)
    )
}