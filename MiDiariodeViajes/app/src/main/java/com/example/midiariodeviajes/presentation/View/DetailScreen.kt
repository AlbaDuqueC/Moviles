package com.example.midiariodeviajes.presentation.View

import androidx.compose.runtime.Composable


@Composable
fun DestinationDetails(destinationId: String?){

    //paso el id de parametros a int
    val id : Int
    id = destinationId?.toInt() ?: 0

}

@Composable
fun MostrarDetalles(){

}