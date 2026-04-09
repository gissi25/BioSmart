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
import sv.ues.fia.eisi.biosmart.R
import sv.ues.fia.eisi.biosmart.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    // El Binding nos permite acceder a los IDs del XML sin usar findViewById
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

        // 1. Aplicamos el color morado a la palabra "Smart"
        configurarLogoColor()

        // 2. Programamos el clic para ir a Registro (cuando lo tengas listo)
        binding.btnIrARegistro.setOnClickListener {
            // Aquí irá la navegación: findNavController().navigate(R.id.action_login_to_registro)
        }
    }

    private fun configurarLogoColor() {
        val textoCompleto = "BioSmart"
        val spannable = SpannableString(textoCompleto)

        // Usamos el NUEVO color morado que definimos en colors.xml
        val colorMorado = ContextCompat.getColor(requireContext(), R.color.logo_smart_morado)

        // Explicación de los números:
        // 'B' es 0, 'i' es 1, 'o' es 2, 'S' es 3.
        // Pintamos desde la posición 3 (la 'S') HASTA la 8 (el final, 't').
        spannable.setSpan(
            ForegroundColorSpan(colorMorado),
            3, // start: donde empieza "Smart"
            8, // end: donde termina
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Asignamos el texto con el formato al TextView del logo
        binding.tvLogo.text = spannable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Limpiamos el binding para evitar fugas de memoria
    }
}