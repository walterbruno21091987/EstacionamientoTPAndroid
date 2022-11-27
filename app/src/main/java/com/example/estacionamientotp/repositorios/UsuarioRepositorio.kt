package com.example.estacionamientotp.repositorios

import android.content.Context
import com.example.estacionamientotp.clases.VehiculoPesado
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.estacionamientotp.clases.Usuario

import com.example.estacionamientotp.excepciones.ExisteUsuarioException
import validarEspacios
import validarLargo
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
object UsuarioRepositorio {
    private val usuarios = mutableListOf<Usuario>()

    init {
        //repeat(10){ i ->
        for (i in 1..10) agregar(
            Usuario(
                id = i.toLong(),
                nombre = "Nombre Test $i",
                apellido = "Apellido Test $i",
                saldo = 1.75 * i,
                fechaAlta = LocalDate.now(),
                password = "password$i",
                nombreUsuario = "usuarioTest$i",
                vehiculo = VehiculoPesado("efr456")
            )
        )
    }

    fun agregar(usuario: Usuario, context:Context?=null): Long {
        return try {
            val userToSave = Usuario(
                usuarios.count().toLong().plus(1),
                usuario.nombre.validarLargo(1, 25),
                usuario.apellido.validarLargo(1, 25),
                usuario.saldo,
                usuario.fechaAlta,
                usuario.vehiculo,
                usuario.password.validarLargo(1, 16).validarEspacios(),
                usuario.nombreUsuario.validarLargo(1, 16).validarEspacios()
            )

            if (existe(usuario.nombreUsuario)) {
                throw ExisteUsuarioException("El usuario ya existe")
            } else if (userToSave.vehiculo.patenteVehiculo.validarLargo(1, 10).isEmpty()){
                throw Throwable("La patente es invalida o existente")
            }
            usuarios.add(userToSave)
            userToSave.id
        } catch (ex: Throwable) {
            if(context!=null) {
                Toast.makeText(context, "Usuario - " + ex.message, Toast.LENGTH_LONG).show()
            }
            0
        }
    }

    fun eliminar(usuario: Usuario) {
        usuarios.remove(usuario)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun eliminar(id: Long) {
        usuarios.removeIf { x -> x.id == id }
    }

    private fun existe(nombreUsuario: String): Boolean {
        return !usuarios.none { it.nombreUsuario == nombreUsuario }
    }

    private fun existe(nombreUsuario: String, password: String): Boolean {
        return usuarios.any { it.nombreUsuario == nombreUsuario && it.password == password }
    }
   fun iniciar(nombreUsuario: String, password: String): Usuario? {
        return if (existe(nombreUsuario, password)) usuarios.first { it.nombreUsuario == nombreUsuario }
        else null
    }
}