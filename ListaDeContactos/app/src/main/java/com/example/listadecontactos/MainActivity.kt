package com.example.listadecontactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.listadecontactos.data.database.ContactoDataBase
import com.example.listadecontactos.ui.presentation.ContactsScreen
import com.example.listadecontactos.ui.theme.ListaDeContactosTheme

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var database: ContactoDataBase
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Room.databaseBuilder(
            applicationContext,			// Contexto de la aplicación
            ContactoDataBase::class.java,		// Clase de la base de datos
            "contacto-db"				// Nombre de la base de datos
        ).build()


        enableEdgeToEdge()
        setContent {
            ListaDeContactosTheme {
                val modifier: Modifier= Modifier.fillMaxSize()
                ContactsScreen(modifier.padding(12.dp))
            }
        }
    }
}

