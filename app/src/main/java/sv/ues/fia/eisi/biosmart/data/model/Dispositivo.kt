package sv.ues.fia.eisi.biosmart.data.model

data class Dispositivo(
    val id: String = "",
    val nombre: String = "ESP32_BioSmart",
    val estaConectado: Boolean = false,
    val ultimaSincronizacion: Long = System.currentTimeMillis()
)
//Esto es lo que va a definir qué datos guardas en Firebase.