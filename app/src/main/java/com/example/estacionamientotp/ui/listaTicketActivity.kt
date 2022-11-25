package com.example.estacionamientotp.ui

import adapter.MyAdapterTickets
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import clases.Ticket
import com.example.estacionamientotp.R
import com.example.estacionamientotp.databinding.ActivityListaTicketBinding
import repositorios.TicketRepositorio

class listaTicketActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding:ActivityListaTicketBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_lista_ticket
        )

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerTickets)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val listTicket= mutableListOf<Ticket>()
        val bundle=intent.extras
        val licensePlate=bundle?.getString("patente")
        if(licensePlate!=null){
       for (i in TicketRepositorio.buscar(licensePlate)) listTicket.add(i)
        recyclerView.adapter=MyAdapterTickets(this,listTicket)
    }
    }
}