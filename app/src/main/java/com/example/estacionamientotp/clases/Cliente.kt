package com.example.estacionamientotp.clases

import java.time.LocalDate

open class Cliente(
    val id: Long,
    val nombre: String,
    val apellido: String,
    var saldo: Double,
    val fechaAlta: LocalDate,
    val vehiculo: Vehiculo
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cliente

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
    fun descuentoCliente(montoRecargo:Double,antiguedad:(LocalDate)->Int):Double{
        var descuento=0.0
        when(antiguedad(fechaAlta)){
            in 0..1->descuento=montoRecargo.times(0.50)
            in 2..6->descuento=montoRecargo.times(0.25)
        }
        return descuento
    }
}