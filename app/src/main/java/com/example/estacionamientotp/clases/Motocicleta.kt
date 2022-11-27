package com.example.estacionamientotp.clases

class Motocicleta(patenteVehiculo:String): Vehiculo(patenteVehiculo){
    override fun calcularRecargo(ticket: Ticket):Double{
        return 0.0
    }
}