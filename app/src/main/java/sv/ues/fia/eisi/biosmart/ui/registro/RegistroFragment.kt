package sv.ues.fia.eisi.biosmart.ui.registro

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

        // Botón Registrar
        binding.btnRegistrarUsuario.setOnClickListener {
            registrarUsuario()
        }

        // Botón Cancelar
        binding.btnCancelarRegistro.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun registrarUsuario() {
        val nombre = binding.etNombreRegistro.text.toString().trim()
        val email = binding.etCorreoRegistro.text.toString().trim()
        val pass = binding.etPassRegistro.text.toString().trim()

        // Validaciones básicas
        if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.length < 6) {
            Toast.makeText(requireContext(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        // 1. Crear usuario en Firebase Authentication
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid

                    // 2. Guardar datos adicionales en Firestore
                    val userMap = hashMapOf(
                        "nombre" to nombre,
                        "correo" to email,
                        "rol" to "usuario", // Por defecto
                        "fecha_registro" to System.currentTimeMillis()
                    )

                    if (userId != null) {
                        db.collection("usuarios").document(userId)
                            .set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(), "¡Registro exitoso!", Toast.LENGTH_LONG).show()
                                // Volver al login o ir al inicio
                                findNavController().popBackStack()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(requireContext(), "Error al guardar datos: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun configurarLogoColor() {
        val textoCompleto = "BioSmart"
        val spannable = SpannableString(textoCompleto)
        val colorMorado = ContextCompat.getColor(requireContext(), R.color.logo_smart_morado)

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