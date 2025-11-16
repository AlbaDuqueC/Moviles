package com.example.pruebadecosa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebadecosa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d("ciclo", "onCreate(1) creado")

        /*setContentView(R.layout.activity_main)

        val boton = findViewById<Button>(R.id.btnACCEDER)

        boton.text = "ACCEDER"*/

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnACCEDER.text = "ACCEDER"

        binding.btnACCEDER.setOnClickListener {
            // Obtener los valores **en el momento del clic**
            val inpUsuario = binding.inputUsuario.text.toString()
            val inpContraseña = binding.inputPassword.text.toString()

            if (inpUsuario == "usuario" && inpContraseña == "usuario") {
                // Abrir la otra pantalla
                val intent = Intent(this, MainMenuActivity::class.java)
                Toast.makeText(
                    this,
                    "INICIANDO SESIÓN",
                    Toast.LENGTH_SHORT
                ).show()
                intent.putExtra("USUARIO_KEY", inpUsuario)
                startActivity(intent)
            } else {
                Log.d(":::tag","Hola")
                    Toast.makeText(
                        this,
                        "ERROR AL INICIAR SESION",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo", "onStart(1) llamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ciclo", "onResume(1) llamado - ¡La Activity es visible y activa!")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo", "onPause(1) llamado - Otra Activity toma el foco")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ciclo", "onRestart(1) llamado - Volviendo de estar 'stopped'")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo", "onStop(1) llamado - La Activity ya no es visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo", "onDestroy(1) llamado - La Activity está siendo destruida")
    }
}