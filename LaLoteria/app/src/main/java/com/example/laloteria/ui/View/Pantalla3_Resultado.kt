package com.example.laloteria.ui.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.laloteria.ui.ViewModel.VMApuesta

@Composable
fun Resultado(
    modifier: Modifier = Modifier,
    destinationViewModel: VMApuesta = viewModel(),
    navController: NavHostController,
    numero: Int,
    apuesta: Int,
){


    val rango= 1..9

    val result= rango.random()

    var dinero: Int

    Column (

        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,



    )
    {
        Text("El resultado es: " + result.toString(), fontSize = 30.sp)

        if (result==numero){

            Text("Enorabuena has acertado")


            destinationViewModel.ganar(apuesta)


        }else{

            Text("Has fallado")

            destinationViewModel.perder(apuesta)

        }

        Button(
            onClick = {
                navController.navigate("Pantalla1_Eleccion")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),

        ) {
            Text("Volver a apostar")
        }
    }




}