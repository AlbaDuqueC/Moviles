package com.example.ejercicio1_boletincomposablesbsicos.ui.theme.ui

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
fun Ejercicio1(modifier: Modifier = Modifier) {
    var texto by remember { mutableStateOf("¡Hola, desconocido!") }

    Text(
        text = texto,
        modifier = modifier
    )
    Button(
        onClick = { texto = "¡Has presionado el botón!" },
        modifier = Modifier
            .size(200.dp, 100.dp)
            .padding(all = 24.dp)
    ) {
        Text(text = "Pulsame")

    }


}