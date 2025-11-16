package com.example.navejemplo.ui.presentation

import android.provider.ContactsContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.navejemplo.data.repository.PersonaRepository

@Composable
fun FormularioPersona(modifier: Modifier = Modifier, onAddContact: () -> Unit) {

    val navController = rememberNavController()

    Column() {
        Row(modifier = Modifier.padding(10.dp)) {
            Text("Nombre: ")
            //para introducir el campo especificado
            CasillaTexto()
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Text("Edad: ")
            //para introducir el campo especificado
            CasillaTexto()
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Text("Género: ")
            //para introducir el campo especificado
            CasillaTexto()
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Button(onClick = {}) {
                Text("GUARDAR", fontSize = 20.sp)
            }
        }
    }
}

//función para crear una casilla de texto que se vaya modificando
@Composable
fun CasillaTexto() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.padding(end = 56.dp, top = 30.dp)
    )
}

@Composable
fun PersonaScreen(modifier: Modifier = Modifier) {
    //Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->

    //}

}