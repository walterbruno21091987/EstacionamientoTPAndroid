package clases

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek


class VehiculoPesado(patenteVehiculo:String): Vehiculo(patenteVehiculo){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun calcularRecargo(ticket: Ticket):Double{
        var recargo=ticket.montoBruto.times(0.05)
        if(ticket.fechaIngreso.dayOfWeek.equals(DayOfWeek.SATURDAY)||
            ticket.fechaIngreso.dayOfWeek.equals(DayOfWeek.SUNDAY))
        {
            recargo=ticket.montoBruto.times(0.10)

        }
        return recargo

    }
}