package com.example.ejercicio1_boletincomposablesbsicos.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Ejercicio2(modifier: Modifier= Modifier){

    Card {
        Column(
            modifier=Modifier
                .padding()
                .size(300.dp, 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text= "Alba Duque",
                modifier= modifier


            )
            Text(
                text= "Informatica",
                modifier=modifier

            )

            Text(
                text = "alba.duque@iesnervion.es",
                modifier= modifier
            )
        }
    }

}