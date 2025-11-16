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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boletin1.ui.ui.theme.Boletin1Theme
import kotlin.random.Random

class Ejercicio3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {

                //texto grande
                Text(
                    "FRASES ALEATORIAS", fontSize = 30.sp, modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
                FrasesAleatorias(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                )
            }
        }
    }
}

@Composable
fun FrasesAleatorias(modifier: Modifier = Modifier) {

    //variable de texto que tiene un estado inicial
    var textoCambiar by remember { mutableStateOf("Si pulsas el botón cambiaré") }

    //el texto es igual a la variable textoCambiar
    Text(
        modifier = modifier,
        text = textoCambiar
    )

    Button(
        //llama a la funcion para crear una nueva frase cuando se pulse el boton
        onClick = { textoCambiar = GenerarFrase() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(70.dp)
    ) {
        Text("Frase aleatoria")
    }

}

fun GenerarFrase(): String {

    //lista de las palabras
    val frases =
        listOf("Sigue adelante", "Nunca te rindas", "El código es poesía", "Aprende algo nuevo hoy")

    //escojo un numero aleatorio
    var numAleatorio = Random.nextInt(0, frases.size)

    //retorno la frase
    return frases[numAleatorio]
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Boletin1Theme {
        FrasesAleatorias(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}