package com.example.estacionamientotp

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import clases.*
import com.example.estacionamientotp.databinding.ActivityRegistrarseBinding
import repositorios.ClienteRepositorio
import repositorios.UsuarioRepositorio
import java.time.LocalDate

class Registrarse_Activity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityRegistrarseBinding=DataBindingUtil.setContentView(this,R.layout.activity_registrarse)

        binding.registrarUsuario.setOnClickListener {
            val nombre=binding.etNombreRegistrarse.text.toString()
            val apellido=binding.etApellidoRegistrarse.text.toString()
            val username=binding.etUsuarioRegistrarse.text.toString()
            val contrasenia=binding.etContraseARegistrarse.text.toString()
            val patente=binding.etPatenteRegistrarse.text.toString()
            var vehiculo:Vehiculo?=null
            if(binding.automovil.isChecked) {
                vehiculo=Automovil(patente)
            }
            if(binding.motocicleta.isChecked) {
                vehiculo=Motocicleta(patente)
            }
            if(binding.vehiculoPesado.isChecked) {
                vehiculo=VehiculoPesado(patente)
            }
            var clientId: Long = 0
            if(vehiculo!=null){
            val userId: Long = UsuarioRepositorio.agregar(Usuario(0, nombre, apellido, 0.0, LocalDate.now(),
                        vehiculo, contrasenia, username),this)
                if (userId > 0) clientId = ClienteRepositorio.agregar(Cliente(0, nombre, apellido, 0.0, LocalDate.now(), vehiculo))
                if (userId > 0 && clientId > 0) {
                    Toast.makeText(this,"Usuario creado correctamente",Toast.LENGTH_LONG).show()
                } else if (userId > 0) {
                    UsuarioRepositorio.eliminar(userId)}
        }}

        }

    }
