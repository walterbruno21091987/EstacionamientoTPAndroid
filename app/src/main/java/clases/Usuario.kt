package clases

import java.time.LocalDate

class Usuario(
    id: Long, nombre: String, apellido: String, saldo:Double, fechaAlta: LocalDate,
    vehiculo: Vehiculo,
    val password: String, val nombreUsuario: String):
    Cliente(id,nombre,apellido,saldo,fechaAlta,vehiculo)
