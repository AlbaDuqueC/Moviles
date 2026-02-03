package com.example.hostaldigital.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hostaldigital.ui.model.UiState
import com.example.hostaldigital.ui.viewModel.AuthViewModel
import com.example.hostaldigital.ui.viewModel.RoomViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    roomId: Int,
    authViewModel: AuthViewModel,
    roomViewModel: RoomViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToBookings: () -> Unit
) {
    val userSession by authViewModel.userSession.collectAsState()
    val selectedRoom by roomViewModel.selectedRoom.collectAsState()
    val bookingState by roomViewModel.bookingState.collectAsState()

    var checkInDate by remember { mutableStateOf(System.currentTimeMillis()) }
    var checkOutDate by remember { mutableStateOf(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    LaunchedEffect(bookingState) {
        when (bookingState) {
            is UiState.Success -> {
                onNavigateToBookings()
                roomViewModel.resetBookingState()
            }
            is UiState.Error -> {
                errorMessage = (bookingState as UiState.Error).message
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Confirmar Reserva") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        selectedRoom?.let { room ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Habitación ${room.roomNumber}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text(text = room.type, style = MaterialTheme.typography.bodyMedium)
                        Text(text = "${room.price}€ / noche", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Fechas", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Entrada: ${dateFormatter.format(Date(checkInDate))}")
                        Text("Salida: ${dateFormatter.format(Date(checkOutDate))}")

                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                        val nights = TimeUnit.MILLISECONDS.toDays(checkOutDate - checkInDate).coerceAtLeast(1)
                        val totalPrice = room.price * nights

                        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                            Text("Total:")
                            Text("${"%.2f".format(totalPrice)}€", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                if (errorMessage != null) {
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 16.dp))
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        val user = userSession.user
                        if (user != null) {
                            roomViewModel.bookRoom(
                                userId = user.id,
                                roomId = room.id,
                                checkInDate = checkInDate,
                                checkOutDate = checkOutDate,
                                status = "Pendiente"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = bookingState !is UiState.Loading
                ) {
                    if (bookingState is UiState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    } else {
                        Text("Confirmar Reserva")
                    }
                }
            }
        }
    }
}