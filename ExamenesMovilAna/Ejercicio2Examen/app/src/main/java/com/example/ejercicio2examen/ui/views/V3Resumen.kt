package com.example.ejercicio2examen.ui.views

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ejercicio2examen.domain.entities.Persona
import com.example.ejercicio2examen.ui.viewmodels.VMPersonasImporte

@Composable
fun ListaPersonasGrupo(
    navController: NavHostController,
    personasVM: VMPersonasImporte
) {
    //almacena la lista de las personas agarrada del viewmodel
    val listaPersonas by personasVM.personas
    Column() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            //recorre la lista y por cada item
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(listaPersonas) { itemPersona ->
                    //la guarda en un item y se lo pasa al destination row
                    LineaPersonaGrupo(
                        persona = itemPersona,
                        navController
                    )
                }

                item{
                    Row(modifier = Modifier.padding(20.dp)) {
                        Button(onClick = {
                            navController.navigate("V1PersonaPorGrupoTotalPagar")
                        }) {
                            Text("Volver al inicio")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LineaPersonaGrupo(
    persona: Persona,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Text(persona.nombre + " debe pagar " + persona.importePagar + "€")
        }
    }
}