package sv.ues.fia.eisi.biosmart.ui.login

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import sv.ues.fia.eisi.biosmart.R
import sv.ues.fia.eisi.biosmart.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Configuración estética del logo
        configurarLogoColor()

        // 2. Lógica de Navegación a las diferentes opciones de Login
        configurarNavegacion()
    }

    private fun configurarNavegacion() {
        // Opción 1: Reconocimiento Facial
        binding.btnFacial.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_facialFragment)
        }

        // Opción 2: Huella Dactilar
        binding.btnHuella.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_huellaFragment)
        }

        // Opción 3: Correo Electrónico
        binding.btnCorreo.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_correoFragment)
        }

        // Opción 4: Ir a Registro (Se activa cuando creés el fragment de registro)
        binding.btnIrARegistro.setOnClickListener {
            // findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }
    }

    private fun configurarLogoColor() {
        val textoCompleto = "BioSmart"
        val spannable = SpannableString(textoCompleto)

        val colorMorado = ContextCompat.getColor(requireContext(), R.color.logo_smart_morado)

        // "Smart" empieza en el índice 3 (B=0, i=1, o=2, S=3)
        spannable.setSpan(
            ForegroundColorSpan(colorMorado),
            3,
            8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.tvLogo.text = spannable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}