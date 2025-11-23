package com.example.ejercicio3examen.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejercicio3examen.ui.viewmodels.VMPersonaPeso

@Composable
fun PesoIdeal(
    navController: NavHostController,
    viewmodel: VMPersonaPeso,
    numero: Int
) {
    //almacena el peso ideal de la persona
    val imc = calcularIMC(viewmodel, numero)

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row() {
            if (imc < 18.5) {
                Text("Peso inferior al normal")
            } else if (imc >= 18.5 && imc <= 24.9) {
                Text("Peso normal")
            } else if (imc >= 25 && imc <= 29.9) {
                Text("Sobrepeso")
            } else {
                Text("Obesidad")
            }
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Button(
                onClick = {
                    navController.navigate("V1DatosPersona")
                }
            ) { Text("Volver al inicio") }
        }
    }

}

fun calcularIMC(
    viewmodel: VMPersonaPeso,
    numero: Int
): Double {
    var imc = viewmodel.getPesoPersona() / (numero * numero)

    //comprobar su sexo
    if (viewmodel.getSexoPersona() == "Masculino") {
        imc *= 1
    } else {
        imc *= 0.95
    }

    return imc
}