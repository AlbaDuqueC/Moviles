package com.example.listadecontactos.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listadecontactos.MainActivity
import com.example.listadecontactos.R
import com.example.listadecontactos.data.entities.Contacto
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var imagenSeleccionada by remember { mutableStateOf("Mujer") }
    val coroutineScope = rememberCoroutineScope()
    var mostrarError by remember { mutableStateOf(false) }

    // Lista de imágenes disponibles
    val imagenesDisponibles = listOf(
        "Mujer" to R.drawable.muriel_plantilla,
        "Hombre" to R.drawable.abuelo
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nuevo Contacto",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo de nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                mostrarError = false
            },
            label = { Text("Nombre *") },
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarError && nombre.isBlank(),
            supportingText = {
                if (mostrarError && nombre.isBlank()) {
                    Text("El nombre es obligatorio")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de apellidos
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de teléfono
        OutlinedTextField(
            value = telefono,
            onValueChange = {
                telefono = it
                mostrarError = false
            },
            label = { Text("Teléfono *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            isError = mostrarError && telefono.isBlank(),
            supportingText = {
                if (mostrarError && telefono.isBlank()) {
                    Text("El teléfono es obligatorio")
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Selección de imagen
        Text(
            text = "Selecciona un género:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            imagenesDisponibles.forEach { (nombreImg, recurso) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        imagenSeleccionada = nombreImg
                    }
                ) {
                    Image(
                        painter = painterResource(id = recurso),
                        contentDescription = nombreImg,
                        modifier = Modifier
                            .size(100.dp)
                            .border(
                                width = if (imagenSeleccionada == nombreImg) 4.dp else 1.dp,
                                color = if (imagenSeleccionada == nombreImg)
                                    MaterialTheme.colorScheme.primary
                                else
                                    Color.Gray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = nombreImg,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp),
                        color = if (imagenSeleccionada == nombreImg)
                            MaterialTheme.colorScheme.primary
                        else
                            Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Botón cancelar
            OutlinedButton(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            // Botón guardar
            Button(
                onClick = {
                    if (nombre.isBlank() || telefono.isBlank()) {
                        mostrarError = true
                    } else {
                        coroutineScope.launch {
                            val nombreCompleto = if (apellidos.isNotBlank()) {
                                "$nombre $apellidos"
                            } else {
                                nombre
                            }

                            val nuevoContacto = Contacto(
                                name = nombreCompleto,
                                phoneNumber = telefono,
                                genero = imagenSeleccionada
                            )

                            MainActivity.database.contactoDao().addContacto(nuevoContacto)
                            navController.navigateUp()
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }
        }
    }
}