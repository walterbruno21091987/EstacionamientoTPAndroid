package com.example.estacionamientotp.ui

import com.example.estacionamientotp.adapter.MyAdapterTickets
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.estacionamientotp.clases.Ticket
import com.example.estacionamientotp.R
import com.example.estacionamientotp.databinding.ActivityListaTicketBinding
import com.example.estacionamientotp.repositorios.TicketRepositorio

class listaTicketActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding:ActivityListaTicketBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_lista_ticket
        )

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerTickets)
        recyclerView.layoutManager=LinearLayoutManager(this)
        var listTicket= mutableListOf<Ticket>()
        val bundle=intent.extras
        val licensePlate=bundle?.getString("patente")
        val activity=bundle?.getInt("activity")
        if(licensePlate!=null && activity!=null){
            for (i in TicketRepositorio.buscar(licensePlate)) listTicket.add(i)
            if(activity==1) {
                listTicket= listTicket.filter { it.pago==false } as MutableList<Ticket>
            }
            recyclerView.adapter = MyAdapterTickets(this,activity, listTicket)
        }
    }
}