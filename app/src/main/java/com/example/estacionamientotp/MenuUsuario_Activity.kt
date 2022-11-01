package com.example.estacionamientotp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.databinding.ActivityMenuUsuarioBinding
import repositorios.ClienteRepositorio

class MenuUsuario_Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)
        val binding:ActivityMenuUsuarioBinding=DataBindingUtil.setContentView(this,R.layout.activity_menu_usuario)
        val bundle=intent.extras
        val idUser=bundle?.getLong("usuario")
        if(idUser!=null){
        val user=ClienteRepositorio.obtenerPorId(idUser)
        }
        binding.cerrarSesion.setOnClickListener {
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}