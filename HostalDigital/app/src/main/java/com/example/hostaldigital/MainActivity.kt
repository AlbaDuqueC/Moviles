package com.example.hostaldigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hostaldigital.ui.theme.HostalDigitalTheme
import com.example.hostaldigital.ui.view.*
import com.example.hostaldigital.ui.viewModel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HostalDigitalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Inicialización de Factoría y ViewModels
                    val factory = ViewModelFactory(LocalContext.current)
                    val authViewModel: AuthViewModel = viewModel(factory = factory)
                    val roomViewModel: RoomViewModel = viewModel(factory = factory)
                    val bookingViewModel: BookingViewModel = viewModel(factory = factory)
                    val ownerViewModel: OwnerViewModel = viewModel(factory = factory)


                    val availableRooms by roomViewModel.availableRooms.collectAsState()

                    NavHost(navController = navController, startDestination = "listaHabitaciones") {

                        // Pantalla de lista de habitaciones disponibles (Principal)
                        composable(route = "listaHabitaciones") {
                            RoomListScreen(
                                authViewModel = authViewModel,
                                roomViewModel = roomViewModel,
                                onNavigateToLogin = { navController.navigate("login") },
                                onNavigateToRegister = { navController.navigate("registro") },
                                onNavigateToRoomDetail = { roomId ->
                                    availableRooms.find { it.id == roomId }?.let { roomViewModel.selectRoom(it) }
                                    navController.navigate("habitacion/$roomId")
                                },
                                onNavigateToBookings = { navController.navigate("listaDeReserva") },
                                onNavigateToOwnerDashboard = { navController.navigate("ownerDashboard") }
                            )
                        }

                        // Pantalla de login
                        composable(route = "login") {
                            LoginScreen(
                                authViewModel = authViewModel,
                                onLoginSuccess = { navController.navigate("listaHabitaciones") },
                                onNavigateToRegister = { navController.navigate("registro") }
                            )
                        }

                        // Pantalla de registro
                        composable(route = "registro") {
                            RegisterScreen(
                                authViewModel = authViewModel,
                                onRegisterSuccess = { navController.navigate("listaHabitaciones") },
                                onNavigateToLogin = { navController.navigate("login") }
                            )
                        }

                        // Pantalla de detalle de habitación
                        composable(
                            route = "habitacion/{roomId}",
                            arguments = listOf(navArgument("roomId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val roomId = backStackEntry.arguments?.getInt("roomId") ?: 0
                            RoomDetailScreen(
                                roomId = roomId,
                                authViewModel = authViewModel,
                                roomViewModel = roomViewModel,
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToBooking = { id -> navController.navigate("reservar/$id") },
                                onNavigateToLogin = { navController.navigate("login") }
                            )
                        }

                        // Pantalla de reservar
                        composable(
                            route = "reservar/{roomId}",
                            arguments = listOf(navArgument("roomId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val roomId = backStackEntry.arguments?.getInt("roomId") ?: 0
                            BookingScreen(
                                roomId = roomId,
                                authViewModel = authViewModel,
                                roomViewModel = roomViewModel,
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToBookings = { navController.navigate("listaDeReserva") }
                            )
                        }

                        // Pantalla de listado de tus reservas / Cancelar reserva (Integrado en la misma vista)
                        composable(route = "listaDeReserva") {
                            UserBookingsScreen(
                                authViewModel = authViewModel,
                                bookingViewModel = bookingViewModel,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }

                        //  RUTAS DE DUEÑO

                        composable(route = "ownerDashboard") {
                            OwnerDashboardScreen(
                                ownerViewModel = ownerViewModel,
                                onNavigateBack = { navController.popBackStack() },
                                onNavigateToAddRoom = { navController.navigate("addRoom") },
                                onNavigateToRoomHistory = { /* Implementar si tienes vista de historial */ }
                            )
                        }

                        composable(route = "addRoom") {
                            AddRoomScreen(
                                ownerViewModel = ownerViewModel,
                                onNavigateBack = { navController.popBackStack() },
                                onRoomAdded = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}