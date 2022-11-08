package com.example.estacionamientotp

import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import clases.Cliente
import clases.Ticket
import clases.Usuario
import com.example.estacionamientotp.databinding.ActivityMenuUsuarioBinding
import excepciones.CodigoDeTicketExistenteException
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
        binding.btGenerarTicket.setOnClickListener {


            if(binding.etGenerarTicket.visibility == View.GONE){
                binding.etGenerarTicket.visibility=View.VISIBLE
            }else binding.etGenerarTicket.visibility=View.GONE

            if(!binding.etGenerarTicket.text.isEmpty()&&user!=null){
                 val numTicket=binding.etGenerarTicket.text.toString().toInt()
                  val nuevoTicket = Ticket(

                      numTicket, LocalDate.now(), LocalTime.now(), user.vehiculo.patenteVehiculo
                  )
                 try{ val agregado = TicketRepositorio.agregar(nuevoTicket)
                  if (agregado) {
                      Toast.makeText(this,"Se registro correctamente el ticket",Toast.LENGTH_LONG).show()
                  }}catch( e: CodigoDeTicketExistenteException){
                     Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
                 }

            }


        }

        binding.btConsultarHistorial.setOnClickListener {

            intent=Intent(this,listaTicketActivity::class.java)
            if(user!=null){
                val patente=user.vehiculo.patenteVehiculo
                intent.putExtra("patente",patente)
            }

            startActivity(intent)

        }
   binding.btPagoTicket.setOnClickListener {
       if(binding.etIngreseTicket.visibility == View.GONE){
           binding.etIngreseTicket.visibility=View.VISIBLE
       }else binding.etIngreseTicket.visibility=View.GONE
       if(!binding.etIngreseTicket.text.isEmpty()){
       intent=Intent(this,Abonar_Ticket_Activity::class.java)
           val codTicket=binding.etIngreseTicket.text.toString().toInt()
           intent.putExtra("codigoTicket",codTicket)
           intent.putExtra("idUser",idUser)
           startActivity(intent)}

   }

        binding.btCargarSaldo.setOnClickListener {
         if(binding.groupRecargarSaldo.visibility==View.GONE){
             binding.groupRecargarSaldo.visibility=View.VISIBLE
         }else{
             binding.groupRecargarSaldo.visibility=View.GONE
         }
            binding.fbEnviarRegargaSaldo.setOnClickListener {
                if(!binding.etMontoARecargar.text.isEmpty()&&idUser!=null){
                    try {    Toast.makeText(this,"Su nuevo saldo es de $ ${ClienteRepositorio.aumentarSaldo(idUser,binding.etMontoARecargar.text.toString())}",Toast.LENGTH_LONG).show()
                }catch (ex: Throwable) {
                Toast.makeText(this,"Error " + ex.message,Toast.LENGTH_LONG).show()
            }

            }




            }
        }
        binding.btSaldo.setOnClickListener {
            if(binding.tvInformarSaldo.visibility==View.GONE){
                binding.tvInformarSaldo.visibility=View.VISIBLE
                if(idUser!=null) {
                    binding.tvInformarSaldo.text = ClienteRepositorio.consultarSaldo(idUser).toString()
                }
                           }
            else{
                binding.tvInformarSaldo.visibility=View.GONE
            }
        }
        binding.cerrarSesion.setOnClickListener {
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}