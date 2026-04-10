package sv.ues.fia.eisi.biosmart.ui.correo

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import sv.ues.fia.eisi.biosmart.R
import sv.ues.fia.eisi.biosmart.databinding.FragmentCorreoBinding

class CorreoFragment : Fragment() {

    private var _binding: FragmentCorreoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCorreoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aplicar el color morado al logo
        configurarLogoColor()

        // Botón cancelar para volver al login
        binding.btnCancelarCorreo.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun configurarLogoColor() {
        val textoCompleto = "BioSmart"
        val spannable = SpannableString(textoCompleto)
        val colorMorado = ContextCompat.getColor(requireContext(), R.color.logo_smart_morado)

        spannable.setSpan(
            ForegroundColorSpan(colorMorado),
            3, // Empieza en la 'S'
            8, // Termina al final
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvLogoCorreo.text = spannable
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}