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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejercicio2examen.domain.entities.Persona
import com.example.ejercicio2examen.ui.viewmodels.VMPersonasImporte

/*@Composable
fun NombresIntegrantes(
    cantidadIntegrantes: Int,
    navController: NavHostController,
    personasVM: VMPersonasImporte = viewModel()
) {
    //se almacena el nombre del integrante
    var nombreIntegrante by remember { mutableStateOf("") }
    //cantidad que tiene que pagar cada integrante
    val cantidadPorIntegrante: Int = personasVM.getCantidadPagar() / cantidadIntegrantes

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text("La cantidad que debe pagar cada integrante es de ${cantidadPorIntegrante}€")
        }
        for (i in 1..cantidadIntegrantes) {
            Row(modifier = Modifier.padding(10.dp)) {
                inputNombreIntegrante(
                    "Introduzca el nombre del integrante ${i}",
                    nombreIntegrante
                ) { }
            }
            //se llama a la funcion del view model para insertar una nueva persona
            personasVM.insertarIntegrante(Persona(i, nombreIntegrante, cantidadPorIntegrante))
        }
    }
}*/

@Composable
fun NombresIntegrantes(
    cantidadIntegrantes: Int,
    navController: NavHostController,
    personasVM: VMPersonasImporte
) {
    var indexActual by remember { mutableStateOf(1) }
    var nombreIntegrante by remember { mutableStateOf("") }
    val cantidadPorIntegrante = personasVM.getCantidadPagar() / cantidadIntegrantes

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text("Cada integrante debe pagar ${cantidadPorIntegrante}")
        }
        Row(modifier = Modifier.padding(10.dp)) {
            inputNombreIntegrante(
                "Introduzca el nombre del integrante ${indexActual}",
                nombreIntegrante,
                onValueChange = { nombreIntegrante = it }
            )
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Button(
                onClick = {
                    // Guardar la persona actual
                    personasVM.insertarIntegrante(
                        Persona(indexActual, nombreIntegrante, cantidadPorIntegrante.toFloat())
                    )

                    if (indexActual == cantidadIntegrantes) {
                        // Si es el último, navegar a la siguiente vista
                        navController.navigate("V3Resumen")
                    } else {
                        // Si no es el último, avanzar al siguiente
                        indexActual++
                        //se limpia el campo
                        nombreIntegrante = ""
                    }
                }
            ) {
                Text(
                    if (indexActual == cantidadIntegrantes) "Enviar" else "Siguiente"
                )
            }
        }
    }
}

//input donde se escribirá el nombre de cada persona
@Composable
fun inputNombreIntegrante(
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