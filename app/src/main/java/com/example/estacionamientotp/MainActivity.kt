package com.example.estacionamientotp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.databinding.ActivityMainBinding
import repositorios.UsuarioRepositorio

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
     binding.ingresar.setOnClickListener {
         val user = UsuarioRepositorio.iniciar(binding.username.text.toString(),binding.password.text.toString())
         if (user != null) {
             intent= Intent(this,MenuUsuario_Activity::class.java)
             intent.putExtra("usuario",user.id)
             startActivity(intent)
         } else {
             Toast.makeText(this,"El usuario ingresado no existe o la password es incorrecta",Toast.LENGTH_LONG).show()
         }
     }

        binding.registrarse.setOnClickListener {
            intent=Intent(this,Registrarse_Activity::class.java)
           startActivity(intent)
        }
     }
    }
