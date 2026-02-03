package com.example.hostaldigital.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hostaldigital.domain.entities.BookingWithDetails
import com.example.hostaldigital.ui.model.Room
import com.example.hostaldigital.ui.model.UiState
import com.example.hostaldigital.ui.viewModel.OwnerViewModel

import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnerDashboardScreen(
    ownerViewModel: OwnerViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAddRoom: () -> Unit,
    onNavigateToRoomHistory: (Int) -> Unit
) {
    val allRooms by ownerViewModel.allRooms.collectAsState()
    val allBookings by ownerViewModel.allBookings.collectAsState()
    val completeBookingState by ownerViewModel.completeBookingState.collectAsState()

    var selectedTab by remember { mutableStateOf(0) }
    var bookingToComplete by remember { mutableStateOf<Int?>(null) }
    var showCompleteDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val tabs = listOf("Habitaciones", "Activas", "Historial")

    LaunchedEffect(completeBookingState) {
        if (completeBookingState is UiState.Success) {
            snackbarHostState.showSnackbar("Check-out completado")
            ownerViewModel.resetCompleteBookingState()
            showCompleteDialog = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel Owner") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) { Icon(Icons.Default.ArrowBack, null) }
                },
                actions = {
                    TextButton(onClick = onNavigateToAddRoom) { Text("Añadir") }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(selected = selectedTab == index, onClick = { selectedTab = index }, text = { Text(title) })
                }
            }

            when (selectedTab) {
                0 -> RoomsTab(allRooms, allBookings, onNavigateToRoomHistory)
                1 -> ActiveBookingsTab(
                    // Filtramos por String "Pendiente" o "Activa"
                    bookings = allBookings.filter { it.booking.status == "Pendiente" || it.booking.status == "Activa" },
                    onCompleteClick = { bookingToComplete = it; showCompleteDialog = true }
                )
                2 -> BookingsHistoryTab(
                    bookings = allBookings.filter { it.booking.status == "Finalizada" || it.booking.status == "Cancelada" }
                )
            }
        }
    }

    if (showCompleteDialog) {
        AlertDialog(
            onDismissRequest = { showCompleteDialog = false },
            title = { Text("¿Check-out?") },
            text = { Text("Se liberará la habitación.") },
            confirmButton = {
                TextButton(onClick = { bookingToComplete?.let { ownerViewModel.completeBooking(it) } }) { Text("Confirmar") }
            },
            dismissButton = {
                TextButton(onClick = { showCompleteDialog = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
fun RoomsTab(rooms: List<Room>, allBookings: List<BookingWithDetails>, onRoomClick: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(rooms) { room ->
            val activeBooking = allBookings.find { it.booking.roomId == room.id && (it.booking.status == "Pendiente" || it.booking.status == "Activa") }
            OwnerRoomCard(room, activeBooking, onClick = { onRoomClick(room.id) })
        }
    }
}

@Composable
fun OwnerRoomCard(room: Room, activeBooking: BookingWithDetails?, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text("Hab. ${room.roomNumber}", fontWeight = FontWeight.Bold)
                Text(if (room.isAvailable) "Disponible" else "Ocupada", color = if (room.isAvailable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
            }
            activeBooking?.let {
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
                Text("Huésped: ${it.user.name}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun ActiveBookingsTab(bookings: List<BookingWithDetails>, onCompleteClick: (Int) -> Unit) {
    if (bookings.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Sin reservas activas") }
    } else {
        LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(bookings) { item ->
                OwnerBookingCard(item) { onCompleteClick(item.booking.id) }
            }
        }
    }
}

@Composable
fun BookingsHistoryTab(bookings: List<BookingWithDetails>) {
    if (bookings.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Historial vacío") }
    } else {
        LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(bookings) { item -> OwnerBookingCard(item, null) }
        }
    }
}

@Composable
fun OwnerBookingCard(bookingWithDetails: BookingWithDetails, onCompleteClick: (() -> Unit)?) {
    val booking = bookingWithDetails.booking
    val user = bookingWithDetails.user

    val statusColor = when(booking.status) {
        "Finalizada" -> MaterialTheme.colorScheme.secondary
        "Cancelada" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Column {
                    Text("Hab. ${bookingWithDetails.room.roomNumber}", fontWeight = FontWeight.Bold)
                    Text(user.name, style = MaterialTheme.typography.bodySmall)
                }
                Text(booking.status, color = statusColor, fontWeight = FontWeight.Bold)
            }
            if (onCompleteClick != null && (booking.status == "Pendiente" || booking.status == "Activa")) {
                Button(onClick = onCompleteClick, modifier = Modifier.padding(top = 8.dp)) {
                    Text("Check-out")
                }
            }
        }
    }
}