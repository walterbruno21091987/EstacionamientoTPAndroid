package clases

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

class Automovil(patenteVehiculo:String): Vehiculo(patenteVehiculo){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun calcularRecargo(ticket: Ticket):Double{
        var recargo=ticket.montoBruto.times(0.03)
        if(ticket.horaIngreso.isAfter(LocalTime.of(9,0))&&ticket.horaIngreso.isBefore(LocalTime.of(11,30))
            ||ticket.horaIngreso.isAfter(LocalTime.of(18,0))&&ticket.horaIngreso.isBefore(LocalTime.of(20,0)) )
        {
            recargo=ticket.montoBruto.times(0.05)

        }
        return recargo

    }
}