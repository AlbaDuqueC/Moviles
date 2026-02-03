package com.example.hostaldigital.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hostaldigital.ui.model.Room
import com.example.hostaldigital.ui.model.UiState
import com.example.hostaldigital.ui.viewModel.OwnerViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRoomScreen(
    ownerViewModel: OwnerViewModel,
    onNavigateBack: () -> Unit,
    onRoomAdded: () -> Unit
) {
    var roomNumber by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("Single") }
    var pricePerNight by remember { mutableStateOf("") }
    var capacity by remember { mutableStateOf("1") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var expandedType by remember { mutableStateOf(false) }

    val addRoomState by ownerViewModel.addRoomState.collectAsState()
    val roomTypes = listOf("Single", "Double", "Suite")

    LaunchedEffect(addRoomState) {
        if (addRoomState is UiState.Success) {
            onRoomAdded()
            ownerViewModel.resetAddRoomState()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Habitación") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Número de Habitación
            OutlinedTextField(
                value = roomNumber,
                onValueChange = { roomNumber = it; errorMessage = null },
                label = { Text("Número de Habitación") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Selector de Tipo (Dropdown)
            ExposedDropdownMenuBox(
                expanded = expandedType,
                onExpandedChange = { expandedType = !expandedType }
            ) {
                OutlinedTextField(
                    value = selectedType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de Habitación") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedType) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedType,
                    onDismissRequest = { expandedType = false }
                ) {
                    roomTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = { selectedType = type; expandedType = false }
                        )
                    }
                }
            }

            // Precio y Capacidad
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = pricePerNight,
                    onValueChange = { pricePerNight = it; errorMessage = null },
                    label = { Text("Precio (€)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = capacity,
                    onValueChange = { capacity = it; errorMessage = null },
                    label = { Text("Capacidad") },
                    leadingIcon = { Icon(Icons.Default.Person, null) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            // Descripción
            OutlinedTextField(
                value = description,
                onValueChange = { description = it; errorMessage = null },
                label = { Text("Descripción") },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage != null) {
                Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val priceVal = pricePerNight.toDoubleOrNull()
                    val capVal = capacity.toIntOrNull()

                    when {
                        roomNumber.isBlank() -> errorMessage = "Falta el número"
                        priceVal == null -> errorMessage = "Precio inválido"
                        capVal == null -> errorMessage = "Capacidad inválida"
                        description.isBlank() -> errorMessage = "Falta descripción"
                        else -> {
                            val newRoom = Room(
                                id = 0, // Room lo genera automáticamente
                                roomNumber = roomNumber,
                                type = selectedType,
                                price = priceVal, // Ajustado a 'price'
                                capacity = capVal,
                                description = description,
                                isAvailable = true
                            )
                            ownerViewModel.addRoom(newRoom)
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = addRoomState !is UiState.Loading
            ) {
                if (addRoomState is UiState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text("Guardar Habitación")
                }
            }
        }
    }
}