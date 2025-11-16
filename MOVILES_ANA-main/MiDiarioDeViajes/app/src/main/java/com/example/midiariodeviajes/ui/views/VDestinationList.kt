package com.example.midiariodeviajes.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.midiariodeviajes.domain.entities.Destination
import com.example.midiariodeviajes.ui.viewmodels.VMDestination

//esta función mostrará una línea para cada elemento (destino)
@Composable
fun DestinationRow(destination: Destination, navController: NavHostController) {
    //card para cada destination
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .clickable { navController.navigate("VDetailScreen/${destination.id}") }) {
            Text(destination.nombre, fontSize = 20.sp)
        }

    }
}

//esta funcion recorrerá la lista de destinos que le llegan del repositorio
//y por cada uno de ellos ejecuta la función destination row para poder mostrar
//linea a linea los destinos
@Composable
fun DestinationList(
    modifier: Modifier = Modifier,
    destinationViewModel: VMDestination = viewModel(),
    navController: NavHostController,
) {
    val destinationList by destinationViewModel.destinations
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        //recorre la lista y por cada item
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(destinationList) { itemDestination ->
                //la guarda en un item y se lo pasa al destination row
                DestinationRow(
                    destination = itemDestination,
                    navController = navController
                )
            }
        }
        /*Button(onClick = { navController.navigate("") }) {
            Text("Ir a Detalles")
        }*/
    }
}