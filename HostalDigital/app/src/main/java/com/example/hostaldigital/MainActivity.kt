package com.example.hostaldigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hostaldigital.ui.theme.HostalDigitalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            NavHost(navController=navController, startDestination = "logout"){

                //Pantalla de logout
                composable(route="logout") {

                }
                //Pantalla de login
                composable(route="login") {

                }
                //Pantalla de registro
                composable(route="registro") {

                }
                //Pantalla de lista de habitaciones disponibles
                composable(route="listaHabitaciones") {

                }

                //Pantalla de habitación
                composable(route="habitacion") {

                }

                //Pantalla de reservar
                composable(route="reservar") {

                }

                //Pantalla de listado de tus reservas
                composable(route="listaDeReserva") {

                }

                //Pantalla para cancelar una reserva
                composable(route="CancelarReserva") {

                }



            }


        }
    }
}

