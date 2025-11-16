package com.example.boletin1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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

class Ejercicio8 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {
                CambiarModo(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                )
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CambiarModo(modifier: Modifier = Modifier) {

    var checked by remember { mutableStateOf(true) }
    //variable que cambiará de valor dependiendo de si el switch se ha presionado
    //o no
    var colorFondo by remember { mutableStateOf(Color.White) }
    //variable que cambiará el texto en función del tema de la app
    var texto by remember { mutableStateOf("Claro") }
    //color del texto
    var colorTexto by remember { mutableStateOf(Color.Black) }

    Scaffold(containerColor = colorFondo) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp)
        ) {

            Row() {
                //muestro lo que haya en la variable
                Text(text = texto,
                    color = colorTexto)
            }
            Row() {
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )

                if (!checked) {
                    colorFondo = Color.Black
                    texto = "Oscuro"
                    colorTexto = Color.White
                } else {
                    colorFondo = Color.White
                    texto = "Claro"
                    colorTexto = Color.Black
                }
            }
        }
    }

}

