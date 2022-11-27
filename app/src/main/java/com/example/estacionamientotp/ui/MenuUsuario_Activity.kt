package com.example.estacionamientotp.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.clases.Cliente
import com.example.estacionamientotp.clases.Ticket
import com.example.estacionamientotp.R
import com.example.estacionamientotp.databinding.ActivityMenuUsuarioBinding
import com.example.estacionamientotp.excepciones.CodigoDeTicketExistenteException
import com.example.estacionamientotp.repositorios.ClienteRepositorio
import com.example.estacionamientotp.repositorios.TicketRepositorio
import java.time.LocalDate
import java.time.LocalTime

class MenuUsuario_Activity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_usuario)
        val binding:ActivityMenuUsuarioBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_menu_usuario
        )

        val bundle=intent.extras
        val idUser=bundle?.getLong("usuario")
       var user: Cliente?=null
        if(idUser!=null){
     user= ClienteRepositorio.obtenerPorId(idUser)
        }
        binding.btGenerarTicket.setOnClickListener {
            generarTicket(user)
        }
        binding.btConsultarHistorial.setOnClickListener {
            historialTicket(user)
        }
        binding.btPagoTicket.setOnClickListener {
       abonarTicketList(user)
        }
        binding.btCargarSaldo.setOnClickListener {
           binding.tvInformarSaldo.visibility=View.GONE
            cargarSaldo(binding, idUser)
        }
        binding.btSaldo.setOnClickListener {
            saldo(binding, idUser)
        }
        binding.cerrarSesion.setOnClickListener {
            intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun abonarTicketList(user: Cliente?) {
        intent = Intent(this, listaTicketActivity::class.java)
        if (user != null) {
            val patente = user.vehiculo.patenteVehiculo
            intent.putExtra("patente", patente)
            intent.putExtra("activity",1)
             startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saldo(
        binding: ActivityMenuUsuarioBinding,
        idUser: Long?
    ) {
        if (binding.tvInformarSaldo.visibility == View.GONE) {
            binding.tvInformarSaldo.visibility = View.VISIBLE
            if (idUser != null) {
                binding.tvInformarSaldo.text = ClienteRepositorio.consultarSaldo(idUser).toString()+" "+"$"
            }
        } else {
            binding.tvInformarSaldo.visibility = View.GONE
        }
    }

    private fun historialTicket(user: Cliente?) {
        intent = Intent(this, listaTicketActivity::class.java)
        if (user != null) {
            val patente = user.vehiculo.patenteVehiculo
            intent.putExtra("patente", patente)
            intent.putExtra("activity",0)
        }

        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generarTicket(user: Cliente?) {
        var existente: Boolean
        do {
            existente = false
            if (user != null) {
                var numTicket = (Math.random().times(1000000).plus(1)).toInt()
                val nuevoTicket = Ticket(
                    numTicket, LocalDate.now(), LocalTime.now(), user.vehiculo.patenteVehiculo
                )
                try {
                    val agregado = TicketRepositorio.agregar(nuevoTicket)
                    if (agregado) {
                        Toast.makeText(
                            this,
                            "Se registro correctamente el ticket numero $numTicket",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: CodigoDeTicketExistenteException) {
                    existente = true
                    numTicket = (Math.random().times(1000000).plus(1)).toInt()
                }
            }
        } while (existente)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun cargarSaldo(
        binding: ActivityMenuUsuarioBinding,
        idUser: Long?
    ) {
        if (binding.groupRecargarSaldo.visibility == View.GONE) {
            binding.groupRecargarSaldo.visibility = View.VISIBLE
        } else {
            binding.groupRecargarSaldo.visibility = View.GONE
        }
        binding.btEnviarRegargaSaldo.setOnClickListener {
            if (!binding.etMontoARecargar.text.isEmpty() && idUser != null) {
                try {
                    Toast.makeText(
                        this,
                        "Su nuevo saldo es de $ ${
                            ClienteRepositorio.aumentarSaldo(
                                idUser,
                                binding.etMontoARecargar.text.toString()
                            )
                        }",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (ex: Throwable) {
                    Toast.makeText(this, "Error " + ex.message, Toast.LENGTH_LONG).show()
                }

            }


        }
    }


}