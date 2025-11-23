package com.example.ejercicio4examen.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun ListaProductosElegir(
    navController: NavHostController,
    viewmodel: VMProductos
) {

    //variable que almacena los datos de la lista
    val listaProductos by viewmodel.productos

    //recorre la lista y por cada item
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(listaProductos) { itemProducto ->
            //la guarda en un item y se lo pasa al destination row
            ProductosLinea(
                producto = itemProducto,
                navController
            )
        }
    }
}

@Composable
fun ProductosLinea(producto: Producto, navController: NavHostController) {

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
                    navController.navigate("V2Comprar/${producto.nombre}")
                }) { Text("Comprar") }
            }
        }
    }
}