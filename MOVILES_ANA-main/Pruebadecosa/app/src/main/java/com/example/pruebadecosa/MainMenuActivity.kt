package com.example.pruebadecosa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.pruebadecosa.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Log.d("ciclo", "onCreate(2) creado")

        val binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recibe el mensaje
        val mensaje = intent.getStringExtra("USUARIO_KEY")
        binding.bienvenida.text = "¡Bienvenido,$mensaje!"

        //cuando se pulse el del telefono
        binding.btnTelefono.setOnClickListener {

            //recojo el telefono
            val numTel = binding.inputIntroducir.text.toString()

            //abre el telefono pulsando ese numero
            val intentTel = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$numTel"))
            startActivity(intentTel)
            finish()
        }

        //cuando se pulse el del navegador
        binding.btnNavegador.setOnClickListener {

            //recojo la busqueda
            val busqueda = binding.inputIntroducir.text.toString()

            val intentNav= Intent(Intent.ACTION_VIEW)
            intentNav.data= "https://www.google.com/search?q=${busqueda}".toUri()
            //empiezo la actividad
            startActivity(intentNav)
            finish()
        }

        //cuando se pulse el del mensaje de texto
        binding.btnMensaje.setOnClickListener {
            //recojo el mensaje
            val numMen = binding.inputIntroducir.text.toString()

            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.data = Uri.parse("smsto:" + numMen) // número escrito en el EditText
            smsIntent.putExtra("sms_body", "Hola desde mi app") // texto opcional
            startActivity(smsIntent)
            finish()
        }

        //cuando se pulse el de compartir
        binding.btnCompartir.setOnClickListener {
            //recojo el mensaje a introducir
            val mensaje = binding.inputIntroducir.text.toString()

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, mensaje) // texto escrito en el EditText
            startActivity(Intent.createChooser(shareIntent, "Compartir con..."))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ciclo", "onStart(2) llamado")
    }

    override fun onResume() {
        super.onResume()
        //muestro los string
        Toast.makeText(this, getString(R.string.toast_actividad_b), Toast.LENGTH_SHORT).show()

        Log.d("ciclo", "onResume(2) llamado - ¡La Activity es visible y activa!")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo", "onPause(2) llamado - Otra Activity toma el foco")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ciclo", "onRestart(2) llamado - Volviendo de estar 'stopped'")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo", "onStop(2) llamado - La Activity ya no es visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo", "onDestroy(2) llamado - La Activity está siendo destruida")
    }

}
