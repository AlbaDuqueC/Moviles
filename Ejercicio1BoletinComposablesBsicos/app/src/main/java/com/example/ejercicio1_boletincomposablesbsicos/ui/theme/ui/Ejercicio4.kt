package com.example.ejercicio1_boletincomposablesbsicos.ui.theme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Ejercicio4(modifier: Modifier= Modifier){
    val colores = listOf<Color>(Color.Red, Color.Blue, Color.Gray, Color.Black, Color.Magenta, Color.Yellow)
    var color by remember { mutableStateOf(Color.Red) }

    Box(
        modifier= Modifier
            .size(100.dp)
            .background(color)
    )
    Button(
        onClick = {color=colores.random()},
        modifier= Modifier
            .padding(20.dp)
            .size(200.dp, 50.dp)

    )

    {
        Text(text = "Cambiar color de la caja")
    }

}