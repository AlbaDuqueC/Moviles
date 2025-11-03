package com.example.prueba1

import android.app.Application
import android.content.Intent
import android.net.Uri
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
        setContentView(R.layout.activity_main)



        val dni: Int = 123456789

        val letras: List<Char> = listOf(
            'T', 'R', 'W', 'A', 'G', 'M', 'Y',
            'F', 'P', 'D', 'X', 'B', 'N', 'J',
            'Z', 'S', 'Q', 'V', 'H', 'L', 'C',
            'K', 'E'
        )

        val res: Int = dni % 23

        var letra= letras[res]

        Log.d("letradni_ej1", letra.toString())





        //Pruebas

        val myIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "1122334455"));
        startActivity(myIntent);


    }





}

