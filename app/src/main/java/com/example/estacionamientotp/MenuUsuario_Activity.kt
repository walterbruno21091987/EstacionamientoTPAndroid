package com.example.estacionamientotp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import clases.Cliente
import clases.Ticket
import clases.Usuario
import com.example.estacionamientotp.databinding.ActivityMenuUsuarioBinding
import repositorios.ClienteRepositorio
import repositorios.TicketRepositorio
import java.time.LocalDate
import java.time.LocalTime

class MenuUsuario_Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)
        val binding:ActivityMenuUsuarioBinding=DataBindingUtil.setContentView(this,R.layout.activity_menu_usuario)
        val bundle=intent.extras
        val idUser=bundle?.getLong("usuario")
       var user: Cliente?=null
        if(idUser!=null){
     user=ClienteRepositorio.obtenerPorId(idUser)
        }
        binding.generarTicket.setOnClickListener {
              if(!binding.etGenerarTicket.text.isEmpty()&&user!=null){
                 val numTicket=binding.etGenerarTicket.text.toString().toInt()
                  val nuevoTicket = Ticket(

                      numTicket, LocalDate.of(2022, 9, 26), LocalTime.of(18, 0), user.vehiculo.patenteVehiculo
                  )
                  val agregado = TicketRepositorio.agregar(nuevoTicket)
                  if (agregado) {
                      Toast.makeText(this,"Se registro correctamente el ticket",Toast.LENGTH_LONG).show()
                  }

              }
            else{
                  Toast.makeText(this,"No se pudo registrar el ticket",Toast.LENGTH_LONG).show()
              }

        }
        binding.cerrarSesion.setOnClickListener {
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}