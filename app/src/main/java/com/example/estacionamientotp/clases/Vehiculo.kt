package com.example.estacionamientotp.clases


abstract class Vehiculo(val patenteVehiculo:String){
    abstract fun calcularRecargo(ticket: Ticket):Double
}