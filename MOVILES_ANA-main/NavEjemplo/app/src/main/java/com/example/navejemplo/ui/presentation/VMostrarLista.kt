package com.example.navejemplo.ui.presentation

import android.R.attr.checked
import android.R.attr.text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.navejemplo.data.repository.PersonaRepository
import com.example.navejemplo.domain.entities.Persona

@Composable
fun PersonaRow(persona: Persona) {
    Column() {
        Row(modifier = Modifier.padding(10.dp)) {
            Card(modifier = Modifier.padding(5.dp)) {
                Row() { Text("Nombre: ${persona.nombre}") }
                Row() { Text("Edad: ${persona.edad}") }
                Row() { Text("Género: ${persona.genero}") }
            }

        }
        Row(modifier = Modifier.padding(10.dp)) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(onClick = { /* acción */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Formulario")
                    }
                },
                content = { innerPadding ->
                    // Contenido de la pantalla
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {}
                }
            )
        }
    }
}

@Composable
fun RadioButtons() {
    var selected by remember { mutableStateOf("Femenino") }

    Column(Modifier.padding(16.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = selected == "Masculino",
                    onClick = { selected = "Femenino" }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = selected == "Femenino", onClick = null)
            Spacer(Modifier.width(8.dp))
            Text("Femenino")
        }

        Spacer(Modifier.height(12.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .selectable(
                    selected = selected == "opcion2",
                    onClick = { selected = "opcion2" }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = selected == "opcion2", onClick = null)
            Spacer(Modifier.width(8.dp))
            Text("Opción 2")
        }
    }
}


@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    val lista = PersonaRepository.getAllPersonas()
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        //recorre la lista y por cada item
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(lista) { itemPersona ->
                //la guarda en un item y se lo pasa al contact row
                PersonaRow(persona = itemPersona)
            }
        }
    }
}