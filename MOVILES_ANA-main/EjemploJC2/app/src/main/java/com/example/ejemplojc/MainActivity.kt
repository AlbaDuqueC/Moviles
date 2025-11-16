package com.example.ejemplojc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplojc.ui.theme.EjemploJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploJCTheme {
                InicioSesion(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
            }
        }
    }
}

@Composable
fun InicioSesion(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.imagen_logo),
            contentDescription = "Imagen de mi gato",
            Modifier.size(200.dp, 200.dp).padding(top = 34.dp)
        )

        Text(
            text = "Inicio de sesión",
            fontSize = 25.sp,
            modifier = Modifier.padding(56.dp),
            color = Color(0, 0, 0, 255),
        )
        Row {
            CasillaIntroducir("Usuario")
        }

    }

}

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
fun CasillaIntroducir(texto: String) {
    Text(
        text = texto,
        fontSize = 10.sp,
        modifier = Modifier.padding(start = 56.dp, top = 56.dp, end = 10.dp),
        color = Color(0, 0, 0, 255),
    )

    CasillaTexto()
}

/*@Preview(showBackground = true)
@Composable
fun InicioSesionPreview() {
    EjemploJCTheme {

    }
}*/