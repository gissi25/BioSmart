package sv.ues.fia.eisi.biosmart.ui.registro

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import sv.ues.fia.eisi.biosmart.R
import sv.ues.fia.eisi.biosmart.databinding.FragmentRegistroBinding
import java.text.SimpleDateFormat
import java.util.*

class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!

    // Motores de Firebase
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarLogoColor()

        // Al tocar el campo de fecha, se abre el calendario (DatePicker)
        binding.etFechaNacimiento.setOnClickListener {
            showDatePickerDialog()
        }

        // Botón Registrar
        binding.btnRegistrarUsuario.setOnClickListener {
            registrarUsuario()
        }

        // Botón Cancelar
        binding.btnCancelarRegistro.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                // Formato estándar DD/MM/AAAA
                val selectedDate = String.format("%02d/%02d/%d", day, month + 1, year)
                binding.etFechaNacimiento.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // No permitir seleccionar fechas futuras
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun registrarUsuario() {
        val nombre = binding.etNombreRegistro.text.toString().trim()
        val email = binding.etCorreoRegistro.text.toString().trim()
        val pass = binding.etPassRegistro.text.toString().trim()
        val fechaNac = binding.etFechaNacimiento.text.toString().trim()

        // Validaciones usando strings.xml
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty() || fechaNac.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.error_campos_vacios), Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.length < 6) {
            Toast.makeText(requireContext(), getString(R.string.error_password_corta), Toast.LENGTH_SHORT).show()
            return
        }

        // Cálculo de edad automático
        val edad = calcularEdad(fechaNac)

        // 1. Crear usuario en Firebase Authentication
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid

                    // 2. Guardar datos adicionales en Firestore (incluyendo edad)
                    val userMap = hashMapOf(
                        "nombre" to nombre,
                        "correo" to email,
                        "fecha_nacimiento" to fechaNac,
                        "edad" to edad,
                        "rol" to "usuario",
                        "fecha_registro" to System.currentTimeMillis()
                    )

                    if (userId != null) {
                        db.collection("usuarios").document(userId)
                            .set(userMap)
                            .addOnSuccessListener {
                                // Usamos el string de éxito y concatenamos la edad calculada
                                val mensajeExito = "${getString(R.string.registro_exitoso)} Edad: $edad años"
                                Toast.makeText(requireContext(), mensajeExito, Toast.LENGTH_LONG).show()
                                findNavController().popBackStack()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(requireContext(), "Error Firestore: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun calcularEdad(fechaNac: String): Int {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val fechaNacimiento = sdf.parse(fechaNac) ?: return 0
            val hoy = Calendar.getInstance()
            val cumple = Calendar.getInstance()
            cumple.time = fechaNacimiento

            var edad = hoy.get(Calendar.YEAR) - cumple.get(Calendar.YEAR)

            // Verificamos si ya pasó su cumpleaños este año
            if (hoy.get(Calendar.DAY_OF_YEAR) < cumple.get(Calendar.DAY_OF_YEAR)) {
                edad--
            }
            edad
        } catch (e: Exception) { 0 }
    }

    private fun configurarLogoColor() {
        val textoCompleto = "BioSmart"
        val spannable = SpannableString(textoCompleto)
        val colorMorado = ContextCompat.getColor(requireContext(), R.color.logo_smart_morado)

        // Coloreamos "Smart" (índices 3 al 8)
        spannable.setSpan(
            ForegroundColorSpan(colorMorado),
            3, 8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvLogoRegistro.text = spannable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}