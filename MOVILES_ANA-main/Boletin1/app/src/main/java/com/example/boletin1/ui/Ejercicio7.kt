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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boletin1.ui.ui.theme.Boletin1Theme

class Ejercicio7 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {
                AjustarTexto(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                )
            }
        }
    }
}

@Composable
fun AjustarTexto(modifier: Modifier = Modifier) {

    //variable que almacenará el tamaño del texto el cuál irá variando
    var tamañoTexto by remember { mutableStateOf(20.dp) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            //texto a mostrar con el texto indicado
            Text("Hola, soy un texto y cambio de tamaño", fontSize = tamañoTexto.value.sp)
        }

        Row {
            //botón que AUMENTA el tamaño de la fuente
            Button(
                onClick = {
                    //se aumenta el tamaño
                    tamañoTexto += 10.dp
                }
            ) { Text("Aumentar tamaño") }

            //botón que AUMENTA el tamaño de la fuente
            Button(
                onClick = {
                    //se aumenta el tamaño
                    tamañoTexto -= 10.dp
                }
            ) { Text("Disminuir tamaño") }
        }
    }

}

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Boletin1Theme {
        Greeting("Android")
    }
}*/