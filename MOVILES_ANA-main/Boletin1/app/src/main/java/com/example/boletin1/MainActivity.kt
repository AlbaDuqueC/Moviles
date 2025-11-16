package com.example.boletin1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boletin1.ui.Ejercicio1
import com.example.boletin1.ui.Ejercicio2
import com.example.boletin1.ui.Ejercicio3
import com.example.boletin1.ui.Ejercicio4
import com.example.boletin1.ui.Ejercicio5
import com.example.boletin1.ui.Ejercicio6
import com.example.boletin1.ui.Ejercicio7
import com.example.boletin1.ui.Ejercicio8
import com.example.boletin1.ui.theme.Boletin1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Boletin1Theme {
                val contexto = LocalContext.current

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio1::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 1")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio2::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 2")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio3::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 3")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio4::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 4")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio5::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 5")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio6::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 6")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio7::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 7")
                    }
                    Button(
                        onClick = {
                            val intent = Intent(
                                contexto, Ejercicio8::class.java
                            )
                            contexto.startActivity(intent)
                        }) {
                        Text("Ir a Ejercicio 8")
                    }
                }

            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Boletin1Theme {
        Boton(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
    }
}*/