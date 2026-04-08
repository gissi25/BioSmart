package sv.ues.fia.eisi.biosmart.data.model

data class Chequeo(
    val id: String = "",
    val pacienteId: String = "",
    val fecha: Long = System.currentTimeMillis(),
    val peso: Double = 0.0,
    val temperatura: Double = 0.0,
    val ritmoCardiaco: Int = 0,
    val estado: String = "Normal" // "Normal" o "Atención"
)
//Esto es lo que va a definir qué datos guardas en Firebase.