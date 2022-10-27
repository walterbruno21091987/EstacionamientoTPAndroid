package com.example.estacionamientotp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.databinding.ActivityMenuUsuarioBinding

class MenuUsuario_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)
        val binding:ActivityMenuUsuarioBinding=DataBindingUtil.setContentView(this,R.layout.activity_menu_usuario)
        binding.cerrarSesion.setOnClickListener {
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}