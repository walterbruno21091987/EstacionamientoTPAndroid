package com.example.estacionamientotp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.estacionamientotp.clases.Ticket
import com.example.estacionamientotp.R
import com.example.estacionamientotp.ui.AbonarTicketActivity
import com.example.estacionamientotp.repositorios.ClienteRepositorio

class MyAdapterTickets(val context:Context,val tipoActivity:Int,val ticketList:List<Ticket>):RecyclerView.Adapter<TicketViewHolder>() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
     val layoutInflater=LayoutInflater.from(parent.context)
        return TicketViewHolder(layoutInflater.inflate(R.layout.item_view,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.render(ticketList[position])
        if(tipoActivity==0)holder.pagar.visibility=View.GONE
        holder.pagar.setOnClickListener {
       val codTicket=ticketList[position].codigo
       val idUser= ClienteRepositorio.obtenerPorPatente(ticketList[position].vehiculoPatente).id
            val intent=Intent(context,AbonarTicketActivity::class.java)
            intent.putExtra("codigoTicket", codTicket)
            intent.putExtra("idUser", idUser)
            context.startActivity(intent)
        }
    }

    override fun getItemCount():Int=ticketList.size
}