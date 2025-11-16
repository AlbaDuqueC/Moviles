package com.example.midiariodeviajes.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DestinationDetails(destinationId: String?){

    //paso el id de parametros a int
    val id : Int
    id = destinationId?.toInt() ?: 0

}

@Composable
fun MostrarDetalles(){

}