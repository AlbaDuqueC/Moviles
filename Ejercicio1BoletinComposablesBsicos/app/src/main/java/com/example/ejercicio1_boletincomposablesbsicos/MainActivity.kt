package com.example.ejercicio1_boletincomposablesbsicos

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejercicio1_boletincomposablesbsicos.ui.theme.Ejercicio1BoletinComposablesBásicosTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio1BoletinComposablesBásicosTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //Ejercicio1()
                    //Ejercicio2()
                    //Ejercicio3()
                    //Ejercicio4()
                    //Ejercicio5()
                    //Ejercicio6()
                    Ejercicio7()


                }
            }
        }
    }
}

//Ejercicio 1
@Composable
fun Ejercicio1(modifier: Modifier = Modifier) {
    var texto by remember { mutableStateOf("¡Hola, desconocido!") }

    Text(
        text = texto,
        modifier = modifier
    )
    Button(
        onClick = { texto = "¡Has presionado el botón!" },
        modifier = Modifier
            .size(200.dp, 100.dp)
            .padding(all = 24.dp)
    ) {
        Text(text = "Pulsame")

    }


}

//Ejercicio 2
@Composable
fun Ejercicio2(modifier: Modifier = Modifier) {

    Card {
        Column(
            modifier = Modifier
                .padding()
                .size(300.dp, 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Alba Duque",
                modifier = modifier


            )
            Text(
                text = "Informatica",
                modifier = modifier

            )

            Text(
                text = "alba.duque@iesnervion.es",
                modifier = modifier
            )
        }
    }

}

//Ejercicio 3
@Composable
fun Ejercicio3(modifier: Modifier = Modifier) {
    val frases =
        listOf("Sigue adelante", "Nunca te rindas", "El código es poesía", "Aprende algo nuevo hoy")
    var texto by remember { mutableStateOf("Texto provisional") }

    Column {
        Text(
            text = texto,
            modifier = modifier
        )
        Button(
            onClick = { texto = frases.random() },
            modifier = Modifier
                .size(200.dp, 100.dp)
                .padding(all = 24.dp)


        ) {
            Text("Frase aleatoria")
        }
    }


}

//Ejercicio 4
@Composable
fun Ejercicio4(modifier: Modifier = Modifier) {
    val colores =
        listOf<Color>(Color.Red, Color.Blue, Color.Gray, Color.Black, Color.Magenta, Color.Yellow)
    var color by remember { mutableStateOf(Color.Red) }

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
    )
    Button(
        onClick = { color = colores.random() },
        modifier = Modifier
            .padding(20.dp)
            .size(200.dp, 50.dp)

    )

    {
        Text(text = "Cambiar color de la caja")
    }

}

//Ejercicio 5
@Composable
fun Ejercicio5(modifier: Modifier = Modifier) {

    var descripcion by remember { mutableStateOf(false) }

    Card {
        Column(
            modifier = Modifier
                .size(300.dp, 500.dp)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {

            Text(
                text = "Alba Duque",
                modifier = Modifier
            )
            Spacer(modifier = modifier)

            Text(
                text = "Informatica",
                modifier = Modifier.padding(all = 10.dp)
            )

            Spacer(modifier = modifier)
            Image(
                painter = painterResource(id = R.drawable.abuelo),
                "",
                modifier = modifier
            )

            Spacer(modifier = modifier)
            Button(onClick = { descripcion = !descripcion }) {

                if (descripcion) {

                    Text(
                        text = "Ver menos"
                    )
                } else {
                    Text(
                        text = "Ver mas"
                    )
                }
            }
            Spacer(modifier = modifier)

            if (descripcion) {

                Text(text = "Hola soy Alba, tengo 19 años y este es mi abuelo")

            }

        }
    }

}

//Ejercicio 6
@Composable
fun Ejercicio6(modifier: Modifier = Modifier) {
    var cont by remember {mutableStateOf(0)}
    var alerta by remember {mutableStateOf(false)}

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = cont.toString(),
            modifier = modifier
        )


        Row {

            // Boton -1
            Button(
                onClick = {
                    if(cont>0) {
                        cont = cont - 1
                    }
                    alerta=false
                }
            ) {
                Text(text = "-")
            }
            //Boton +1
            Button(
                onClick = {

                    if (cont < 10) {
                        cont =cont + 1
                        alerta=false
                    }else{
                        alerta=true
                    }
                }
            ) {
                Text(text = "+")
            }
        }


        //boton reiniciar
        Button(
            onClick = {
                cont=0
                alerta=false
            }
        ) {
            Text(text = "Reiniciar")
        }

        //Alerta de maximo alcanzado
        if(alerta){
            Text(
                text = "¡Máximo alcanzado!",
                modifier=modifier
            )

        }
    }

}

//Ejercicio 7
@Composable
fun Ejercicio7 (modifier: Modifier= Modifier){

    var tamaño by remember { mutableStateOf(20) }

    Column {
        Text(
            text = "texto ajustable",
            fontSize=(tamaño.sp),
            modifier= modifier
        )
        Row {
            Button(
                onClick = {
                    tamaño+=1
                }
            ) {
                Text(
                    text = "Aumentar tamaño",
                    modifier=modifier
                )
            }

            Button(
                onClick = {
                    tamaño-=1
                }
            ) {
                Text(
                    text = "Disminuir tamaño",
                    modifier=modifier
                )
            }
        }


    }


}

//Ejercicio 8
@Composable
fun Ejercicio8 (modifier: Modifier= Modifier){



}


@Composable
fun InsertaBoton(texto: String, destino: String) {
    val contexto = LocalContext.current
    Button(onClick = {
        val intent = Intent(contexto, destino::class.java)
        contexto.startActivity(intent)
    }) {
        Text(texto)
    }
}