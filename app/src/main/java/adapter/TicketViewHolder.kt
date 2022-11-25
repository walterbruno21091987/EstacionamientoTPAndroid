package adapter

import android.os.Build
import android.view.View
import android.widget.Button
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
    val estado=view.findViewById<TextView>(R.id.estado)
    val pagar=view.findViewById<Button>(R.id.bt_pagar_ticket_lista)
    @RequiresApi(Build.VERSION_CODES.O)
    fun render(ticket: Ticket) {
        val user=ClienteRepositorio.obtenerPorPatente(ticket.vehiculoPatente)
        nroTicket.text = ticket.codigo.toString()
        patente.text=ticket.vehiculoPatente
        montoBruto.text=ticket.calcularMontoBruto().toString()
        tiempoEstadia.text=ticket.calcularEstadia().toString()
        recargoVehiculo.text=user.vehiculo.calcularRecargo(ticket).toString()
        descuentoCiente.text=ticket.calcularDescuentoCliente(user).toString()
        estado.text=ticket.obtenerEstadoDeTicket()
        montoFinal.text=ticket.calcularMontoFinal(user).toString()
        if(estado.text.toString().equals("PAGO")){
            pagar.visibility= View.GONE
        }else {
            pagar.visibility=View.VISIBLE
        }
    }

}