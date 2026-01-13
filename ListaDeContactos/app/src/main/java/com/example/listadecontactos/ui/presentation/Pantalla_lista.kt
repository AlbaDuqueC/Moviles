package com.example.listadecontactos.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listadecontactos.MainActivity
import com.example.listadecontactos.R
import com.example.listadecontactos.data.entities.Contacto
import kotlinx.coroutines.launch

@Composable
fun ContactRow(
    contacto: Contacto,
    onDelete: (Contacto) -> Unit
) {
    var mostrarInicial by remember { mutableStateOf(true) }
    var mostrarDialogoEliminar by remember { mutableStateOf(false) }

    // Mapeo de género a recurso drawable
    val imagenRecurso = when (contacto.genero) {
        "Mujer" -> R.drawable.muriel_plantilla
        "Hombre" -> R.drawable.abuelo
        // Compatibilidad con datos antiguos
        "muriel" -> R.drawable.muriel_plantilla
        "abuelo" -> R.drawable.abuelo
        "Femenino" -> R.drawable.muriel_plantilla
        "Masculino" -> R.drawable.abuelo
        else -> R.drawable.abuelo
    }

    if (mostrarDialogoEliminar) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoEliminar = false },
            title = { Text("Eliminar contacto") },
            text = { Text("¿Estás seguro de que quieres eliminar a ${contacto.name}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete(contacto)
                        mostrarDialogoEliminar = false
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogoEliminar = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imagenRecurso),
                contentDescription = contacto.name,
                modifier = Modifier
                    .size(80.dp)
                    .clickable { mostrarInicial = !mostrarInicial }
            )

            Spacer(modifier = Modifier.width(12.dp))

            if (!mostrarInicial) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = contacto.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = contacto.phoneNumber,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = { mostrarDialogoEliminar = true }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            } else {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = contacto.name.firstOrNull()?.uppercaseChar()?.toString() ?: "?",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(navController: NavController) {
    var contactos by remember { mutableStateOf<List<Contacto>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    // Función para recargar contactos
    fun recargarContactos() {
        coroutineScope.launch {
            contactos = MainActivity.database.contactoDao().getAllContacto()
        }
    }

    // Cargar contactos al iniciar
    LaunchedEffect(Unit) {
        recargarContactos()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Mis Contactos (${contactos.size})") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("pantalla_formulario")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir contacto"
                )
            }
        }
    ) { innerPadding ->
        if (contactos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No hay contactos",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Presiona + para añadir uno",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(contactos) { contacto ->
                    ContactRow(
                        contacto = contacto,
                        onDelete = { contactoAEliminar ->
                            coroutineScope.launch {
                                MainActivity.database.contactoDao().deleteContacto(contactoAEliminar)
                                recargarContactos()
                            }
                        }
                    )
                }
            }
        }
    }

    // Recargar lista cuando volvemos de añadir contacto
    LaunchedEffect(navController.currentBackStackEntry) {
        recargarContactos()
    }
}