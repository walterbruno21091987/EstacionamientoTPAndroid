package com.example.estacionamientotp.clases

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.time.temporal.ChronoUnit

class Ticket(
    val codigo: Int,
    val fechaIngreso: LocalDate,
    val horaIngreso: LocalTime,
    val vehiculoPatente: String,
    var estadia: Int = 0,
    var montoBruto: Double = 0.0,
    var montoFinal: Double = 0.0,
    var pago:Boolean=false
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun calcularEstadia(): Int {
        estadia = ChronoUnit.DAYS.between(fechaIngreso, LocalDate.now())
            .toInt().times(24).plus((LocalTime.now().hour.minus(horaIngreso.hour)))
        if(estadia>=0&&estadia<1){
            estadia=1
        }
        return estadia
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calcularMontoBruto(): Double {
        calcularEstadia()
        montoBruto = estadia.times(150.0)
        return montoBruto
    }

    fun calcularMontoConRecargo(user: Cliente): Double {
        return montoBruto.plus(user.vehiculo.calcularRecargo(this))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calcularDescuentoCliente(user: Cliente): Double {
        return user.descuentoCliente(calcularMontoConRecargo(user)) { Period.between(it, LocalDate.now()).months }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calcularMontoFinal(user: Cliente): Double {
        montoFinal = calcularMontoConRecargo(user).minus(calcularDescuentoCliente(user))
        return montoFinal
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun imprimirTicket(user: Cliente): String {
        return """ ticket numero : $codigo
        |       patente: $vehiculoPatente
        |       tiempo de estadia: ${calcularEstadia()}
        |       monto bruto:${calcularMontoBruto()}
        |       recargo por vehiculo:${calcularMontoConRecargo(user)}
        |       descuento por cliente:${calcularDescuentoCliente(user)}
        |       monto final:${calcularMontoFinal(user)}
        |       
    """.trimMargin()
    }
    fun obtenerEstadoDeTicket():String{
        var estado="IMPAGO"
        if(pago==true){
            estado="PAGO"
        }
        return estado
    }
}
