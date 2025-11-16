package com.example.prueba

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //variable que contiene el dni
        var dni: Int = 26569805

        var dniString: String = "ayuda"

        //variable que almacena el resu
        var resultado: Int

        var contador: Int = 0

        var letras = listOf<String>("T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E")

        //almaceno el numero resultante
        resultado = dni % 23

        dniString = dni.toString()

        for (letra in letras) {
            if (resultado == contador) {
                dniString += letra
            }
            contador += 1
        }

        Log.d("PUTA MIERDA",dniString)

    }
}