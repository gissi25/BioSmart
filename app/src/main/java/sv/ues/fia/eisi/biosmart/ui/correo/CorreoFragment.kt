package sv.ues.fia.eisi.biosmart.ui.correo

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
import sv.ues.fia.eisi.biosmart.R
import sv.ues.fia.eisi.biosmart.databinding.FragmentCorreoBinding

class CorreoFragment : Fragment() {

    private var _binding: FragmentCorreoBinding? = null
    private val binding get() = _binding!!

    // 1. Necesitamos declarar la variable auth para que el código sepa qué es
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCorreoBinding.inflate(inflater, container, false)

        // 2. Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarLogoColor()

        // 3. Programar el botón para que realmente intente iniciar sesión
        binding.btnIniciarSesion.setOnClickListener {
            val email = binding.etCorreo.text.toString().trim()
            val pass = binding.etPass.text.toString().trim()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                iniciarSesion(email, pass)
            } else {
                Toast.makeText(requireContext(), "Llená todos los campos, Karla", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancelarCorreo.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun iniciarSesion(email: String, pass: String) {
        Toast.makeText(requireContext(), "Verificando credenciales...", Toast.LENGTH_SHORT).show()

        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "¡Bienvenida de nuevo!", Toast.LENGTH_SHORT).show()

                    // 4. Asegúrate que en el NavGraph la flecha tenga este ID:
                    findNavController().navigate(R.id.action_correoFragment_to_dashboardFragment)

                } else {
                    val errorMsg = task.exception?.message ?: "Error desconocido"
                    Toast.makeText(requireContext(), "Fallo al entrar: $errorMsg", Toast.LENGTH_LONG).show()
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
        binding.tvLogoCorreo.text = spannable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}