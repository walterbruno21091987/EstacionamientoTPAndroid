fun String.validarLargo(minLength: Int, maxLength: Int): String =
    if (this.length in minLength..maxLength) this else throw Exception("El valor debe tener un min de $minLength y un max de $maxLength caracteres.")

fun String.validarEspacios(): String =
    if (this.indexOf(" ") == -1) this else throw Exception("El valor contiene espacios.")

fun String.esPositivo(): Double {
    return try {
        if (this.toDouble() > 0) this.toDouble() else throw Exception("El valor no es mayor a cero")
    } catch (ex: Throwable) {
        throw Exception("El valor no es monto valido.")
    }
}