package com.example.pesoideal.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.pesoideal.ui.viewModel.VMPesoIDeal

//Aquí se deberá mostrar el resultado según los valores
// introducidos anteriormente y mediante esta fórmula:
//  IMC  = ( Peso(kg) / Altura(m) * Altura(m) ) * coeficiente
//donde coeficiente será 1 para hombre y 0.95 para mujeres.

//El resultado que deberá mostrar será uno de los siguientes valores de referencia según la OMS:
//Bajo peso: IMC < 18.5
//Peso normal: IMC entre 18.5 y 24.9
//Sobrepeso: IMC entre 25 y 29.9
//Obesidad: IMC ≥ 30

@Composable
fun Resultado(
    modifier: Modifier,
    VMPeso: VMPesoIDeal = viewModel(),
    NavController: NavHostController
){
    var result= VMPeso.CalcularResultado()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (result<18.5){
            Text("Bajo peso")
        }else if (result<24.9){
            Text("Peso normal")
        }else if(result<29.9){
            Text("Sobrepeso")
        }else{
            Text("Obesidad")
        }

        Button(
            onClick = {
                NavController.navigate("Pantalla1"){
                    popUpTo ("Pantalla1"){inclusive=true}
                }

            }
        ) {

            Text("Volver")
        }

    }


}