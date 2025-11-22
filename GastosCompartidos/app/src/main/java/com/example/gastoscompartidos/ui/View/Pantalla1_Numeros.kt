package com.example.gastoscompartidos.ui.View

import android.graphics.pdf.content.PdfPageGotoLinkContent
import android.widget.EditText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gastoscompartidos.ui.ViewModel.VMGastos

//n esta pantalla se indicará el número de personas del grupo y el total a pagar.
//Un botón “Continuar” pasará a la siguiente pantalla.

@Composable
fun Eleccion(
    modifier: Modifier= Modifier,
    destinationViewModel: VMGastos,
    navController: NavHostController
){
    var personas by remember {mutableStateOf("")}
    var dinero by remember {mutableStateOf("")}

    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //Label para introducir datos (numero de personas)
        OutlinedTextField(
            value = personas,
            onValueChange = {personas= it},
            label = {Text("Numero de personas")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        //Label para introducir datos (cantidad de dinero)
        OutlinedTextField(
            value = dinero,
            onValueChange = {dinero= it},
            label = {Text("Dinero para dividir")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        boton(dinero, personas, destinationViewModel, navController, modifier)

    }

}

@Composable
fun boton(
    dinero: String,
    persona: String,
    destinationViewModel: VMGastos,
    navController: NavController,
    modifier: Modifier = Modifier

){

    Button(
        onClick = {
            destinationViewModel.IntroducirDatos(persona.toInt(), dinero.toInt())
            navController.navigate("Pantalla2_Nombres/${persona}")
        }
    ) {
        Text("Continuar")
    }


}

