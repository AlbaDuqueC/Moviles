package com.example.boletn2_jpcnavegacin.ui.views

import androidx.compose.runtime.Composable

class VDetailScreen {

    @Composable
    fun DestinationDetails(destinationId: String?){

        //paso el id de parametros a int
        val id : Int
        id = destinationId?.toInt() ?: 0

    }

    @Composable
    fun MostrarDetalles(){

    }
}