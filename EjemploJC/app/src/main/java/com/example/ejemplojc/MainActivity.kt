package com.example.ejemplojc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ejemplojc.ui.theme.EjemploJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploJCTheme {

                Saludo(
                    modifier = Modifier.fillMaxSize().padding(),

                    name = "Android",
                )

            }
        }
    }
}

@Composable
fun Saludo(name: String, modifier: Modifier = Modifier) {

    Column {


        Text(
            text = "Hello $name!",
            modifier = modifier.padding()
        )
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(
            onClick = {

            }
        ) {
            Text("Pulsa")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SaludoPreview() {
    EjemploJCTheme {
        Saludo("Android")
    }
}