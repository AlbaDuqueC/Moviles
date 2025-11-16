package com.example.boletin1.ui

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boletin1.ui.ui.theme.Boletin1Theme
import kotlin.random.Random
import kotlin.text.get

class Ejercicio4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {

                CambiarColor(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                )

            }
        }
    }
}

@Composable
fun CambiarColor(modifier: Modifier = Modifier) {

    //variable que cambiara
    var colorCaja by remember { mutableStateOf(Color.Red) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //caja de color que por defecto es el color rojo
        Box(
            Modifier
                .size(100.dp)
                .background(colorCaja)
        )

        //botón que cambiará el color de la caja
        Button(
            //cuando se pulse el botón se cambiará el estado de la caja
            onClick = { colorCaja = colorAleatorio() }
        ) { Text("Pulsa para cambiar el color de la caja") }
    }
}

//función para seleccionar un color aleatorio
fun colorAleatorio(): Color {

    //lista de los colores
    var listaColores = listOf<Color>(Color.Red, Color.Blue, Color.Black, Color.Green)

    //creo un numero aleatorio para escoger un color
    var numeroAleatorio = Random.nextInt(0, listaColores.size)

    return listaColores[numeroAleatorio]
}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Boletin1Theme {
        Greeting("Android")
    }
}*/