package com.example.ejercicio3examen.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejercicio3examen.ui.viewmodels.VMPersonaPeso
import java.util.Locale

@Composable
fun AsignarAltura(
    navController: NavHostController,
    viewmodel: VMPersonaPeso,
    nombre: String?
) {
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text("Hola ${nombre} con sexo ${
                viewmodel.getSexoPersona().lowercase(Locale.getDefault())
            }, seleccione su edad por favor")
        }
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items((150..220).toList()) { numero ->
                Button(onClick = {
                    //se crea un objeto persona
                    //val persona = Persona(nombre, numero, textoBoton)
                    navController.navigate("V3PesoIdeal/${numero}")
                }) {
                    Text(numero.toString())
                }
            }
        }
    }
}