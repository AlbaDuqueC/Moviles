package com.example.hostaldigital.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hostaldigital.ui.model.Room
import com.example.hostaldigital.ui.viewModel.AuthViewModel
import com.example.hostaldigital.ui.viewModel.RoomViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomListScreen(
    authViewModel: AuthViewModel,
    roomViewModel: RoomViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToRoomDetail: (Int) -> Unit,
    onNavigateToBookings: () -> Unit,
    onNavigateToOwnerDashboard: () -> Unit
) {
    val userSession by authViewModel.userSession.collectAsState()
    val availableRooms by roomViewModel.availableRooms.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("JetPack Stay Rooms") },
                actions = {
                    if (userSession.isLoggedIn) {
                        Text(
                            text = userSession.user?.name ?: "",
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        if (userSession.isOwner) {
                            TextButton(onClick = onNavigateToOwnerDashboard) {
                                Text("Panel")
                            }
                        } else {
                            TextButton(onClick = onNavigateToBookings) {
                                Text("Reservas")
                            }
                        }

                        IconButton(onClick = { authViewModel.logout() }) {
                            // Icono estándar (disponible siempre)
                            Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar Sesión")
                        }
                    } else {
                        TextButton(onClick = onNavigateToLogin) {
                            Text("Entrar")
                        }
                        TextButton(onClick = onNavigateToRegister) {
                            Text("Registro")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (availableRooms.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "NO VACANCY",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "No hay habitaciones disponibles",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(availableRooms) { room ->
                    RoomCard(
                        room = room,
                        onClick = { onNavigateToRoomDetail(room.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun RoomCard(
    room: Room,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Habitación ${room.roomNumber}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${room.price}€/noche",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono estándar
                Icon(
                    Icons.Default.Home,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = room.type,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(16.dp))
                // Icono estándar
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${room.capacity} pers.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = room.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}