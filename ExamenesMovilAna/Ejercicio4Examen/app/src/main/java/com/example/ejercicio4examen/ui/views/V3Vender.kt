package com.example.ejercicio4examen.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ejercicio4examen.domain.entities.Producto
import com.example.ejercicio4examen.ui.viewmodels.VMProductos

@Composable
fun VenderProducto(
    navController: NavHostController,
    viewmodel: VMProductos
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Text("Saldo tras la compra: ${viewmodel.getCreditosUsuario()}", fontSize = 20.sp)
        }
        for (producto in viewmodel.getAllProductosInventario()) {
            ProductosLineaVender(producto, navController, viewmodel)
        }
        Row(modifier = Modifier.padding(20.dp)) {
            Button(onClick = {
                navController.navigate("V1ElegirProducto")
            }) { Text("Volver") }
        }
    }
}

@Composable
fun ProductosLineaVender(producto: Producto, navController: NavHostController, viewmodel: VMProductos) {

    //variable que servirá para poder mostrar la descripción del artículo o no
    var mostrar by remember { mutableStateOf(false) }
    Card(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    //mostrar se iguala a su estado contrario
                    mostrar = !mostrar
                }) {
            Text(producto.nombre, fontSize = 23.sp)
        }
        //si mostrar está a true se mostrará la descripción del producto
        if (mostrar) {
            Row(modifier = Modifier.padding(20.dp)) {
                Text(producto.descripcion)
            }
            Row(modifier = Modifier.padding(10.dp)) {
                Button(onClick = {
                    //se suman los creditos del producto vendido al usuario
                    viewmodel.setCreditosUsuario(viewmodel.getCreditosUsuario() + producto.precio)
                    //se elimina el producto del inventario
                    viewmodel.eliminarProductoInventario(producto)
                    navController.navigate("V1ElegirProducto")
                }) { Text("Vender") }
            }
        }
    }
}
