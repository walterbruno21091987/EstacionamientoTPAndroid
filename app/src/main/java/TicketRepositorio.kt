package repositorios

import android.os.Build
import androidx.annotation.RequiresApi
import clases.Ticket
import excepciones.CodigoDeTicketExistenteException
import excepciones.NoExisteTicketExeption
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
object TicketRepositorio {
    private val tickets = mutableListOf<Ticket>()

    init {
        //repeat(10){ i ->
        for (i in 1..10) agregar(
            Ticket(
                codigo = i,
                fechaIngreso = LocalDate.now(),
                horaIngreso = LocalTime.now(),
                vehiculoPatente = "hck987",
                estadia = 0,
                montoBruto =0.0,
                montoFinal =0.0
            )
        )
    }

    fun agregar(ticket: Ticket):Boolean  {
    var agregado=false
     try {
         if (tickets.none { it.codigo == ticket.codigo })  agregado=tickets.add(ticket)
         else throw CodigoDeTicketExistenteException("Codigo existente")
     }catch( e:CodigoDeTicketExistenteException){
         println(e.message)
        }
        return agregado
    }

    fun eliminar(ticket: Ticket) {
        tickets.remove(ticket)
    }

    fun obtenerPorId(id: Int): Ticket {
        var ticketADevolver: Ticket? = null
        for (ticket in tickets) {
            if (ticket.codigo == id) {
                ticketADevolver = ticket
            }
        }
        return ticketADevolver ?: throw NoExisteTicketExeption("ticket inexistente")
    }


    fun buscar(patente: String): List<Ticket> {
        return tickets.filter { it.vehiculoPatente == patente }
    }

}