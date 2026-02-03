package com.example.hostaldigital.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.ui.model.UiState
import com.example.hostaldigital.ui.viewModel.AuthViewModel
import com.example.hostaldigital.ui.viewModel.BookingViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBookingsScreen(
    authViewModel: AuthViewModel,
    bookingViewModel: BookingViewModel,
    onNavigateBack: () -> Unit
) {
    val userSession by authViewModel.userSession.collectAsState()
    val userBookings by bookingViewModel.userBookings.collectAsState()
    val cancelState by bookingViewModel.cancelState.collectAsState()

    var bookingToCancel by remember { mutableStateOf<Int?>(null) }
    var showCancelDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(userSession.user?.id) {
        userSession.user?.id?.let { userId ->
            bookingViewModel.loadUserBookings(userId)
        }
    }

    LaunchedEffect(cancelState) {
        if (cancelState is UiState.Success) {
            snackbarHostState.showSnackbar("Reserva cancelada")
            bookingViewModel.resetCancelState()
            showCancelDialog = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Reservas") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (userBookings.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Text("No tienes reservas", style = MaterialTheme.typography.titleLarge)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(userBookings) { item ->
                    BookingCard(
                        bookingWithDetails = item,
                        onCancelClick = {
                            bookingToCancel = item.booking.id
                            showCancelDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showCancelDialog) {
        AlertDialog(
            onDismissRequest = { showCancelDialog = false },
            title = { Text("¿Cancelar reserva?") },
            text = { Text("Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(onClick = {
                    bookingToCancel?.let { bookingViewModel.cancelBooking(it) }
                }) { Text("Confirmar") }
            },
            dismissButton = {
                TextButton(onClick = { showCancelDialog = false }) { Text("Volver") }
            }
        )
    }
}

@Composable
fun BookingCard(
    bookingWithDetails: BookingWithDetails,
    onCancelClick: () -> Unit
) {
    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    val booking = bookingWithDetails.booking
    val room = bookingWithDetails.room

    // ARREGLO DE STATUS: Comparamos Strings directamente
    val statusColor = when (booking.status) {
        "Pendiente", "Activa" -> MaterialTheme.colorScheme.primary
        "Cancelada" -> MaterialTheme.colorScheme.error
        "Finalizada" -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.outline
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text("Habitación ${room.roomNumber}", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)

                // Etiqueta de Status
                Surface(color = statusColor.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small) {
                    Text(
                        text = booking.status,
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Text(room.type, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Entrada: ${dateFormatter.format(Date(booking.checkInDate))}", style = MaterialTheme.typography.bodyMedium)
            Text("Salida: ${dateFormatter.format(Date(booking.checkOutDate))}", style = MaterialTheme.typography.bodyMedium)

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text("Total: ${"%.2f".format(booking.totalPrice)}€", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

                // Solo mostramos el botón si la reserva no está ya cancelada
                if (booking.status != "Cancelada" && booking.status != "Finalizada") {
                    OutlinedButton(
                        onClick = onCancelClick,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}