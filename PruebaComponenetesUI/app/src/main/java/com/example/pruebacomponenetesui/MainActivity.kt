package com.example.pruebacomponenetesui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pruebacomponenetesui.ui.theme.PruebaComponenetesUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaComponenetesUITheme {

                Column() {
                    Botones()
                }



            }
        }
    }
}

@Composable
fun Botones(){

    //Boton normal
    Button(onClick = { /* acción */ }) {
        Text("Aceptar")
    }

    OutlinedButton(onClick = { }) {
        Text("Cancelar")
    }

    TextButton(onClick = { }) {
        Text("Solo texto")
    }

    IconButton(onClick = { }) {
        Icon(Icons.Default.Favorite, contentDescription = null)
    }


}