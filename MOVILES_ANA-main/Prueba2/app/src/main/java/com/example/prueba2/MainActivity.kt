package com.example.prueba2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            /*setContentView(R.layout.activity_main)

            val boton = findViewById<Button>(R.id.btnACCEDER)

            boton.text = "ACCEDER"*/

            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.btnMiboton1.text = "PÚLSAME YA"

            binding.btnMiboton1.setOnClickListener {
                val toast = Toast.makeText(
                    applicationContext,
                    "SIIIIIIIIIIIIIII",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
}