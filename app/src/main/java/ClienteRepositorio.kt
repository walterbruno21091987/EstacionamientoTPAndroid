package repositorios

import clases.Automovil
import android.os.Build
import androidx.annotation.RequiresApi

import clases.Cliente

import excepciones.NoExisteIdDeClienteException
import esPositivo
import validarLargo
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
object ClienteRepositorio {
    private val clientes = mutableSetOf<Cliente>()

    init {
        //repeat(10){ i ->
        for (i in 1..10) agregar(
            Cliente(
                id = i.toLong(),
                nombre = "Nombre Test $i",
                apellido = "Apellido Test $i",
                saldo = 1.75 * i,
                fechaAlta = LocalDate.now(),
                vehiculo = Automovil("hck987")
            )
        )
    }

    fun agregar(cliente: Cliente): Long {
        return try {
            val clientToSave = Cliente(
                clientes.count().toLong().plus(1),
                cliente.nombre.validarLargo(1, 25),
                cliente.apellido.validarLargo(1, 25),
                cliente.saldo,
                cliente.fechaAlta,
                cliente.vehiculo
            )
            clientes.add(clientToSave)
            clientToSave.id
        } catch (ex: Throwable) {
            println(("Cliente - " + ex.message))
            0
        }
    }

    fun eliminar(cliente: Cliente) {
        clientes.remove(cliente)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminar(id: Long) {
        clientes.removeIf { x -> x.id == id }
    }

    fun obtenerPorId(id: Long): Cliente {
        var client: Cliente? = null
        for (cliente in clientes) {
            if (cliente.id.equals(id)) {
                client = cliente
            }
        }

        return client ?: throw NoExisteIdDeClienteException("no existe el ID")
    }

//    private fun obtenerPorId(id: Long): Cliente {
//        return clientes.single { it.id == id }
//    }

    fun buscar(apellido: String, nombre: String): List<Cliente> {
        return clientes.filter { it.apellido == apellido && it.nombre == nombre }
    }

    fun aumentarSaldo(id: Long, funds: String): Double {
        try {
            obtenerPorId(id).saldo += funds.esPositivo()
        } catch (ex: Throwable) {
            println("Error " + ex.message)
        }
        return obtenerPorId(id).saldo
    }

    fun consultarSaldo(id: Long): Double {
        return obtenerPorId(id).saldo
    }

    fun reducirSaldo(id: Long, funds: Double): Double {
        try {
            obtenerPorId(id).saldo -= funds
        } catch (ex: Throwable) {
            println("Error " + ex.message)
        }
        return obtenerPorId(id).saldo
    }
}