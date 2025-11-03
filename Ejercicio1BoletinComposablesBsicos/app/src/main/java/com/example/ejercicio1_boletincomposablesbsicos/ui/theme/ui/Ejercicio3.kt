package com.example.ejercicio1_boletincomposablesbsicos.ui.theme.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Ejercicio3 (modifier: Modifier= Modifier){
    val frases = listOf("Sigue adelante", "Nunca te rindas", "El código es poesía", "Aprende algo nuevo hoy")
    var texto by remember { mutableStateOf("Texto provisional") }

    Column {
        Text(
            text = texto,
            modifier = modifier
        )
        Button(
            onClick = { texto = frases.random() },
            modifier = Modifier
                .size(200.dp, 100.dp)
                .padding(all = 24.dp)


        ) {
            Text("Frase aleatoria")
        }
    }


}