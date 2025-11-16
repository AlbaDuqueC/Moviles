package com.example.boletin1.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boletin1.ui.ui.theme.Boletin1Theme

class Ejercicio1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {
                Ejercicio1(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )

            }
        }
    }
}

//ejercicio 1
@Composable
fun Ejercicio1(modifier: Modifier = Modifier) {

    //variable de texto que tiene un estado inicial
    var textoCambiar by remember { mutableStateOf("¡Hola, desconocido!") }

    //el texto es igual a la variable textoCambiar
    Text(
        modifier = modifier,
        text = textoCambiar
    )
    Button(
        //cuando se presiona el botón la variable es igual a la nueva cadena
        onClick = { textoCambiar = "¡Has pulsado el botón!" },
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        //se muestra el texto
        Text(text = "Pulsame")
    }
}