package sv.ues.fia.eisi.biosmart.data.model

data class Usuario(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val edad: Int = 0,
    val tipoSangre: String = "",
    val pesoReferencia: Double = 0.0,
    val tieneHuella: Boolean = false
)
//Esto es lo que va a definir qué datos guardas en Firebase.