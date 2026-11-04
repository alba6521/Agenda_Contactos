import kotlin.system.exitProcess

//  estructura de datos
data class Contacto(
    val nombre: String,
    val telefono: String,
    val email: String
) {
    override fun toString() = "$nombre - $telefono - $email"
}

val agenda = mutableListOf<Contacto>()

// Expresiones regulares para validar los datos
val REGEX_TELEFONO = Regex("^[0-9]{9}$")
val REGEX_EMAIL = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$")

fun main() {
    var opcion: Int?

    do {
        mostrarMenu()
        opcion = leerLinea().trim().toIntOrNull()

        when (opcion) {
            1 -> anadirContacto()
            2 -> listarContactos()
            3 -> buscarContacto()
            4 -> eliminarContacto()
            5 -> println("\n¡Hasta pronto!")
            else -> println("Opcion no valida. Introduce un numero del 1 al 5.")
        }
    } while (opcion != 5)
}

// menu
fun mostrarMenu() {
    println()
    println("===== AGENDA DE CONTACTOS =====")
    println("1. Añadir contacto")
    println("2. Listar contactos")
    println("3. Buscar contacto")
    println("4. Eliminar contacto")
    println("5. Salir")
    print("Seleccione una opcion: ")
}

// Utilidades de entrada

// Lee una linea. Si la entrada se cierra (Ctrl+D) termina el programa limpiamente.
fun leerLinea(): String {
    val linea = readlnOrNull()
    if (linea == null) {
        println("\nEntrada finalizada. Saliendo del programa.")
        exitProcess(0)
    }
    return linea
}

// Pide un dato hasta que no este vacio y cumpla la validacion (si se indica)
fun leerCampo(
    mensaje: String,
    mensajeError: String = "",
    valido: (String) -> Boolean = { true }
): String {
    while (true) {
        print(mensaje)
        val texto = leerLinea().trim()
        when {
            texto.isEmpty() -> println("Error: este campo es obligatorio y no puede estar vacio.")
            !valido(texto) -> println("Error: $mensajeError")
            else -> return texto
        }
    }
}

// añadir contacto ----------
fun anadirContacto() {
    println("\n--- Anadir contacto ---")
    val nombre = leerCampo("Introduce el nombre: ")
    val telefono = leerCampo(
        "Introduce el telefono: ",
        "el telefono debe tener exactamente 9 digitos, sin espacios y sin letras."
    ) { REGEX_TELEFONO.matches(it) }
    val email = leerCampo(
        "Introduce el correo electronico: ",
        "el correo no tiene un formato valido (ejemplo: ana@email.com)."
    ) { REGEX_EMAIL.matches(it) }

    val nuevo = Contacto(nombre, telefono, email)

    // Evita duplicados exactos (mismo nombre y telefono)
    val duplicado = agenda.any {
        it.nombre.equals(nuevo.nombre, ignoreCase = true) && it.telefono == nuevo.telefono
    }
    if (duplicado) {
        println("Error: ya existe un contacto con ese nombre y telefono.")
        return
    }

    agenda.add(nuevo)
    // Orden alfabetico: la numeracion mostrada coincide siempre con la posicion real
    agenda.sortBy { it.nombre.lowercase() }

    println("Contacto anadido correctamente.")
}

// listar contactos
fun listarContactos() {
    println("\n--- Lista de contactos ---")
    if (agenda.isEmpty()) {
        println("La agenda no contiene contactos.")
        return
    }
    mostrarLista(agenda)
}

fun mostrarLista(contactos: List<Contacto>) {
    contactos.forEachIndexed { indice, contacto ->
        println("${indice + 1}. $contacto")
    }
}

// buscar contacto
fun buscarContacto() {
    println("\n--- Buscar contacto ---")
    if (agenda.isEmpty()) {
        println("La agenda no contiene contactos.")
        return
    }

    val texto = leerCampo("Introduce el nombre a buscar: ")
    val resultados = agenda.filter { it.nombre.contains(texto, ignoreCase = true) }

    if (resultados.isEmpty()) {
        println("No se han encontrado contactos con ese nombre.")
    } else {
        println("Resultados encontrados (${resultados.size}):")
        resultados.forEach { println("- $it") }
    }
}

// eliminar contacto
fun eliminarContacto() {
    println("\n--- Eliminar contacto ---")
    if (agenda.isEmpty()) {
        println("La agenda no contiene contactos.")
        return
    }

    mostrarLista(agenda)
    print("Introduce el numero del contacto a eliminar: ")
    val posicion = leerLinea().trim().toIntOrNull()

    if (posicion == null || posicion !in 1..agenda.size) {
        println("Error: el contacto no existe. Introduce un numero entre 1 y ${agenda.size}.")
        return
    }

    agenda.removeAt(posicion - 1)
    println("Contacto eliminado correctamente.")
}