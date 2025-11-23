package com.example.ejercicio4examen.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ejercicio4examen.domain.entities.Producto
import com.example.ejercicio4examen.ui.viewmodels.VMProductos
import kotlin.random.Random

@Composable
fun ComprarProducto(
    navController: NavHostController,
    viewmodel: VMProductos,
    nombreProducto: String?
) {

    //variable para almacenar el precio del producto
    val precioProducto = ObtenerPrecioProducto(nombreProducto, viewmodel)

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Text("Has comprado: ${nombreProducto}")
        }
        Row(modifier = Modifier.padding(20.dp)) {
            Text("Precio del producto: ${precioProducto}")
        }
        Row(modifier = Modifier.padding(20.dp)) {
            BotonSiguientePantalla(navController, viewmodel, precioProducto, nombreProducto)
            BotonVolverAtras(navController)
        }
    }
}

@Composable
fun BotonSiguientePantalla(
    navController: NavHostController,
    viewmodel: VMProductos,
    precioProducto: Double,
    nombreProducto: String?
) {
    //se agarra el contexto actual para el mensaje toast
    val context = LocalContext.current
    //se almacena el saldo actual del usuario
    val saldoActual = viewmodel.getCreditosUsuario()

    Button(
        onClick = {
            //si el usuario tiene suficientes créditos, compra el producto
            if (saldoActual >= precioProducto) {
                //se actualiza el saldo del usuario
                viewmodel.setCreditosUsuario(saldoActual - precioProducto)
                //se agrega el nuevo producto al inventario del usuario
                viewmodel.insertarProductoInventario(
                    BuscarProducto(
                        nombreProducto,
                        viewmodel
                    )
                )

                navController.navigate("V3Vender")
            } else {
                //si no, se le avisa de que no puede comprarlo
                Toast.makeText(context, "¡Ups! No tienes créditos suficientes", Toast.LENGTH_SHORT)
                    .show()
            }
        }) { Text("Confirmar") }
}

@Composable
fun BotonVolverAtras(
    navController: NavHostController,
){
    Button(
        onClick = {
            navController.navigate("V1ElegirProducto")
        }
    ) { Text("Cancelar")}
}

//funcion para encontrar un producto en específico
fun BuscarProducto(nombreProducto: String?, viewmodel: VMProductos): Producto {

    //variable auxiliar para almacenar el producto
    var productoActual: Producto = Producto(0, "", "")


    //se busca el nombre del producto en la lista de los productos
    for (producto in viewmodel.getAllProductos()) {
        if (producto.nombre == nombreProducto) {
            productoActual = producto
        }
    }

    return productoActual
}

fun ObtenerPrecioProducto(nombreProducto: String?, viewmodel: VMProductos): Double {

    //variable para almacenar el precio del producto
    var precioProducto: Double = 0.0
    //variable auxiliar que almacena el producto actual
    var productoActual: Producto = Producto(0, "", "")

    BuscarProducto(nombreProducto, viewmodel)

    precioProducto = VariacionAleatoriaPrecioMercado(productoActual.precio)

    return precioProducto
}

fun VariacionAleatoriaPrecioMercado(precioProducto: Double): Double {
    //variable que almacena el precio de venta al publico del producto
    var precioProductoPublico: Double
    //variable para almacenar la opcion aleatoria
    var opcionAleatoria: Boolean
    //se almacena la opción aleatoria
    opcionAleatoria = Random.nextBoolean()

    //se le asigna un valor a la variable dependiendo de la opcion aleatoria
    if (opcionAleatoria) {
        precioProductoPublico = precioProducto * 1.20
    } else {
        precioProductoPublico = precioProducto * 0.80
    }

    return precioProductoPublico
}