package com.example.prueba2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba2.databinding.ActivityMainBinding
import com.example.prueba2.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_menu)

        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usuario = intent.getStringExtra("mensaje")
        binding.bienvenido.text="¡Bienvenido $usuario !"

        //BOTONES
        binding.navegador.setOnClickListener {

            val intent= Intent(Intent.ACTION_VIEW)
            intent.data= "https://www.google.com/search?q=${binding.mensajetexto.text}".toUri()
            startActivity(intent)


        }

        binding.telefono.setOnClickListener {

            val myIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + binding.mensajetexto.text));
            startActivity(myIntent);


        }

        binding.mensaje.setOnClickListener {
            val numero = binding.mensajetexto.text.toString()

            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.data = Uri.parse("smsto:" + numero)
            smsIntent.putExtra("sms_body", "Holiwis")
            startActivity(smsIntent)


        }

        binding.compartir.setOnClickListener {
            val mensaje = binding.mensajetexto.text.toString()
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, mensaje)
            startActivity(Intent.createChooser(shareIntent, "Compartir con..."))

        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo", "onCreate() creado")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ciclo",  "onStart() llamado")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,  getString(R.string.toast_actividad_b), Toast.LENGTH_SHORT).show()
        Log.d("ciclo", "onResume() llamado - ¡La Activity es visible y activa!")

    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo",  "onPause() llamado - Otra Activity toma el foco")
    }


    override fun onStop() {
        super.onStop()
        Log.d("ciclo", "onRestart() llamado - Volviendo de estar 'stopped'")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo", "Estoy en onDestroy")
    }
}