package com.example.pesoideal.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pesoideal.ui.viewModel.VMPesoIDeal

//Se mostrará el nombre, la opción elegida en la pantalla anterior
// y se deberá poder introducir la altura (usa una pequeña lista
// scrollable) desde 150 cm hasta 220 cm.
//Al pulsar en cualquier medida se pasará a la tercera pantalla.

@Composable
fun Altura(

    modifier: Modifier,
    VMPeso: VMPesoIDeal = viewModel(),
    navController: NavHostController

){

    val alturas = (150..220).toList()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Datos recibidos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Nombre: ${VMPeso.getNombre()}")
        Text(text = "Sexo elegido: ${VMPeso.getSexo()}")

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Selecciona tu altura:",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),   // Lista pequeña y desplazable
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(alturas) { altura ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { VMPeso.setAltura(altura.toDouble()) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "$altura cm")
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("Pantalla3") }
        ) {
            Text("Continuar")
        }
    }

}

