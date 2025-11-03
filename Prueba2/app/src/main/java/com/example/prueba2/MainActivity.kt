package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        //val boton= findViewById<Button>(R.id.miBoton)
        //boton.text = "Entrar"

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       /* binding.miBoton.setOnClickListener {
            val toast = Toast.makeText(
                applicationContext,
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                Toast.LENGTH_SHORT
            ).show()
        }
*/


        binding.miBoton.setOnClickListener {
            val usuario= binding.usuario.text.toString()
            val contr= binding.contr.text.toString()
            if(usuario=="admin"  && contr=="1234" ){




                    val inte = Intent(this, MainMenuActivity::class.java)

                     inte.putExtra("mensaje", usuario)
                     startActivity(inte)

            }else{

                    val toast = Toast.makeText(
                        applicationContext,
                        "Usuario o contraseña incorrecta",
                        Toast.LENGTH_SHORT
                    ).show()

            }

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
        Log.d("ciclo", "onResume() llamado - ¡La Activity es visible y activa!")
        getString(R.string.toast_actividad_b)
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