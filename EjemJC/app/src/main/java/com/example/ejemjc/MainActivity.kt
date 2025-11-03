package com.example.ejemjc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemjc.ui.theme.EjemJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemJCTheme {

                Saludo(
                    modifier = Modifier.fillMaxSize().padding(all = 18.dp),

                    name = "Android",
                )

            }
        }
    }
}

@Composable
fun Saludo(name: String, modifier: Modifier = Modifier) {

        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally


        ){
            Image(
                painter = painterResource(id = R.drawable.gato),
                "Imagen de i gato",
                Modifier.size(300.dp, 300.dp).padding(top = 100.dp)
            )

            Text(
                text = "Inicio de sesión",
                fontSize = 30.sp,
                modifier = Modifier.padding(8.dp).padding(top = 50.dp),
                color = Color(23, 27, 159, 255),
            )

            Text(
                text = "Usuario",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp).padding(top = 20.dp),
                //color = Color(223, 27, 159, 255),
            )
            SimpleTextField(modifier = Modifier.fillMaxSize().padding(all = 18.dp))

            Text(
                text = "Contraseña",
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp),

            )
            SimpleTextField(modifier = Modifier.fillMaxSize().padding(all = 18.dp))

            Boton(modifier = Modifier.fillMaxSize().padding(all = 18.dp).padding(top = 20.dp))


        }

}

@Composable
fun Boton(modifier: Modifier) {
    val context= LocalContext.current

        Button(
            onClick = {       Toast.makeText(context,"HOLA",Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            // Texto del botón
            Text(
                text = "Entrar",
                fontSize = 18.sp, // Tamaño de fuente
                color = Color.White
            )
        }

}

@Composable
fun SimpleTextField(modifier: Modifier) {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    TextField(
        value = text,
        onValueChange = {
            text = it		// Lo que se escriba en ese momento
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SaludoPreview() {
    EjemJCTheme {
        Saludo("Android")
    }
}