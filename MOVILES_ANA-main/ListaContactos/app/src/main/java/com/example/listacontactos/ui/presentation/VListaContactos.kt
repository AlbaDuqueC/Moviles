package com.example.listacontactos.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listacontactos.R
import com.example.listacontactos.data.repositories.Repositorio
import com.example.listacontactos.domain.entities.Contacto

@Composable
fun ContactRow(contacto: Contacto) {

    //la variable para mostrar y ocultar al principio vale false
    var mostrarLetras by remember { mutableStateOf(value = false) }

    Card(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
        Row {
            //imagen de las iniciales
            var imagenIniciales by remember {
                mutableStateOf(
                    value = contacto.name.firstOrNull()?.uppercaseChar().toString()
                )
            }

            Box(
                modifier = Modifier
                    .width(500.dp)
                    .clickable { mostrarLetras = !mostrarLetras }
                    .background(Color.Cyan)
                    .padding(10.dp)
            ) {
                Column() {
                    Row() {
                        if (contacto.genero == "Femenino") {
                            //imagen e iniciales
                            Image(
                                painter = painterResource(id = R.drawable.imagen1),
                                contentDescription = "Foto contacto",
                                Modifier
                                    .height(70.dp)
                                    .width(70.dp)
                                    .padding(end = 20.dp)
                            )
                        } else {
                            //imagen e iniciales
                            Image(
                                painter = painterResource(id = R.drawable.burranco),
                                contentDescription = "Foto contacto",
                                Modifier
                                    .height(70.dp)
                                    .width(70.dp)
                                    .padding(end = 20.dp)
                            )
                        }
                        Text(
                            //se muestran las inciiales
                            imagenIniciales, fontSize = 30.sp
                        )
                    }

                    //si es true se muestran los datos de los contactos
                    if (mostrarLetras) {
                        Spacer(modifier = Modifier.padding(4.dp))
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
                    }

                }

            }
        }
    }
}

@Composable
fun ContactsScreen(modifier: Modifier = Modifier) {
    val lista = Repositorio.getAllListaContactos()
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        //recorre la lista y por cada item
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(lista) { itemContacto ->
                //la guarda en un item y se lo pasa al contact row
                ContactRow(contacto = itemContacto)
            }
        }
    }
}


