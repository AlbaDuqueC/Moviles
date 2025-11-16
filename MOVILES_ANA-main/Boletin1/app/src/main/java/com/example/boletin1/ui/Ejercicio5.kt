package com.example.boletin1.ui

import android.R.attr.id
import android.R.attr.visible
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.boletin1.ui.ui.theme.Boletin1Theme

class Ejercicio5 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {
                MostrarTarjeta(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(50.dp)
                )
            }
        }
    }
}

@Composable
fun MostrarTarjeta(modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //imagen
            Row {
                //icono de la persona
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuario"
                )
            }
            Row {
                Text("Joselín")
            }
            Row {
                Text("Panadero de todo")
            }
            //fila para enseñar un botón
            Row {
                MasInformacion()
            }
        }
    }
}

@Composable
fun MasInformacion() {
    var visible by remember { mutableStateOf(false) }
    Column {
        AnimatedVisibility(
            visible = visible,
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce aliquam euismod erat dictum auctor.",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        Button(onClick = { visible = if (visible == true) false else true }) {
            if (visible) {
                Text("Ver menos")
            } else {
                Text("Ver más")
            }
        }
    }

}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Boletin1Theme {
        Greeting("Android")
    }
}*/