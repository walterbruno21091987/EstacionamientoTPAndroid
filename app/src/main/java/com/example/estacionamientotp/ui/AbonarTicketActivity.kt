package com.example.estacionamientotp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.R
import com.example.estacionamientotp.databinding.ActivityAbonarTicketBinding
import com.example.estacionamientotp.excepciones.SaldoInsuficienteException
import com.example.estacionamientotp.repositorios.ClienteRepositorio
import com.example.estacionamientotp.repositorios.TicketRepositorio

class AbonarTicketActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityAbonarTicketBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_abonar_ticket
        )
 val bundle=intent.extras
        val numeroTicket=bundle?.getInt("codigoTicket")
        val idUser=bundle?.getLong("idUser")
        if(numeroTicket!=null&&idUser!=null) {
            val cliente = ClienteRepositorio.obtenerPorId(idUser)
            val ticketPrint = TicketRepositorio.obtenerPorId(numeroTicket)
            binding.patente.text = ticketPrint.vehiculoPatente.toString()
            binding.numeroTicket.text = ticketPrint.codigo.toString()
            binding.tiempoEstadia.text = ticketPrint.calcularEstadia().toString()
            binding.montoBruto.text = ticketPrint.calcularMontoBruto().toString()
            binding.recargoVehiculo.text = cliente.vehiculo.calcularRecargo(ticketPrint).toString()
            binding.tvEstado.text = ticketPrint.obtenerEstadoDeTicket()
            binding.descuentoCliente.text = ticketPrint.calcularDescuentoCliente(cliente).toString()
            binding.montoFinal.text=ticketPrint.calcularMontoFinal(cliente).toString()
        }


    binding.imPagar.setOnClickListener {

            try { if(numeroTicket!=null&&idUser!=null){
         val ticket = TicketRepositorio.obtenerPorId(numeroTicket)
                if (ClienteRepositorio.consultarSaldo(idUser) >= ticket.montoFinal&&ticket.pago==false) {
             Toast.makeText(this,"Se ha abonado el ticket correctamente,su nuevo saldo es de ${ClienteRepositorio.reducirSaldo(idUser, ticket.montoFinal)}",Toast.LENGTH_LONG).show()
                    ticket.pago=true
                } else throw SaldoInsuficienteException("Saldo insuficiente")
         }
     } catch (e: SaldoInsuficienteException) {
        Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
     }

 }
        binding.volverMenuUsuario.setOnClickListener {
            intent=Intent(this,MenuUsuario_Activity::class.java)
            intent.putExtra("usuario",idUser)
            startActivity(intent)
        }

    }
}