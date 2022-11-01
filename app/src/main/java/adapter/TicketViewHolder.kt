package adapter

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import clases.Ticket
import com.example.estacionamientotp.R
import repositorios.ClienteRepositorio
import repositorios.UsuarioRepositorio

class TicketViewHolder(view: View):ViewHolder(view) {
    val nroTicket = view.findViewById<TextView>(R.id.numero_ticket)
    val montoBruto = view.findViewById<TextView>(R.id.monto_bruto)
    val montoFinal = view.findViewById<TextView>(R.id.monto_final)
    val patente=view.findViewById<TextView>(R.id.patente)
    val tiempoEstadia=view.findViewById<TextView>(R.id.tiempo_estadia)
    val recargoVehiculo=view.findViewById<TextView>(R.id.recargo_vehiculo)
    val descuentoCiente=view.findViewById<TextView>(R.id.descuento_cliente)
    @RequiresApi(Build.VERSION_CODES.O)
    fun render(ticket: Ticket) {
        val user=ClienteRepositorio.obtenerPorPatente(ticket.vehiculoPatente)
        nroTicket.text = ticket.codigo.toString()
        patente.text=ticket.vehiculoPatente
        montoBruto.text=ticket.calcularMontoBruto().toString()
        tiempoEstadia.text=ticket.calcularEstadia().toString()
        montoFinal.text=ticket.calcularMontoFinal(user).toString()
      recargoVehiculo.text=ticket.calcularMontoConRecargo(user).toString()
        descuentoCiente.text=ticket.calcularDescuentoCliente(user).toString()
    }
}