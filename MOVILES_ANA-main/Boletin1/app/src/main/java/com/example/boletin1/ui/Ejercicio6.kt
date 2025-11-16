package com.example.boletin1.ui

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boletin1.ui.ui.theme.Boletin1Theme

class Ejercicio6 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {
                Contador(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                )
            }
        }
    }
}

@Composable
fun Contador(modifier: Modifier) {
    //agarro el contexto
    val contexto = LocalContext.current

    //numero por defecto que se encuentra a 0
    var numero by remember { mutableStateOf(0) }

    //columna donde irán todos los componentes
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            //muestro el contador
            Text("$numero")
        }

        Row {
            //dos botones
            //BOTÓN DE SUMA
            Button(onClick = {
                if (numero <= 9) {
                    numero += 1
                } else {
                    //le muestra una advertencia
                    Toast.makeText(contexto, "¡Máximo alcanzado!", Toast.LENGTH_SHORT).show()
                }
            }) { Text("+") }

            //BOTÓN DE RESTA
            Button(onClick = {
                if (numero >= 1) {
                    numero -= 1
                } else {
                    Toast.makeText(contexto, "¡Mínimo alcanzado!", Toast.LENGTH_SHORT).show()
                }
            }) { Text("-") }
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