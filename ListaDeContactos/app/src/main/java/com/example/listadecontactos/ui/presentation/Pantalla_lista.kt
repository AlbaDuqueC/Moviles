package com.example.listadecontactos.ui.presentation


import android.R.attr.name
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.listadecontactos.R

import com.example.listadecontactos.data.entities.Contacto
import com.example.listadecontactos.domain.repositories.Repositorio
import com.example.listadecontactos.domain.repositories.Repositorio.listaContactos
import java.io.BufferedReader

@Composable
fun ContactRow(contacto: Contacto) {

    var mostrarInicial by remember { mutableStateOf(true) }



    Card(modifier = Modifier.fillMaxWidth()) {
        Row {
            if (contacto.genero=="Femenino"){
                Image(
                    painter = painterResource(id = R.drawable.muriel_plantilla),
                    contentDescription = "Femenino",
                    modifier = Modifier.height(100.dp)
                        .clickable { mostrarInicial = !mostrarInicial }
                )
            }else{
                Image(
                    painter = painterResource(id = R.drawable.abuelo),
                    contentDescription = "Masculino",
                    modifier = Modifier.height(100.dp)
                        .clickable { mostrarInicial = !mostrarInicial }
                )
            }


            Spacer(modifier = Modifier.padding(4.dp))
            if (!mostrarInicial) {
                Column {
                    Text(
                        text = contacto.name,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = contacto.phoneNumber,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )

                }
            }else{
                Column {
                    Text(
                        text = contacto.name.firstOrNull()?.uppercaseChar()?.toString() ?: "?",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

        }


    }


}



@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    val lista = Repositorio.getAllContacts()
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {    // Scroll vertical,
            items(lista) { itemContacto ->
                ContactRow(contacto = itemContacto)
            }
        }
    }
    Button(
        onClick ={
            navController.navigate("Pantalla_formulario")

        }

    ) {
        Text("+")
//        modifier= Modifier.
    }
}
