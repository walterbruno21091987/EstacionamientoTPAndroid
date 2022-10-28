package adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import clases.Ticket
import com.example.estacionamientotp.R

class MyAdapterTickets(val ticketList:List<Ticket>):RecyclerView.Adapter<TicketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
     val layoutInflater=LayoutInflater.from(parent.context)
        return TicketViewHolder(layoutInflater.inflate(R.layout.item_view,parent,false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.render(ticketList[position])
    }

    override fun getItemCount():Int=ticketList.size
}