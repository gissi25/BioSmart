package sv.ues.fia.eisi.biosmart.ui.facial

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
import sv.ues.fia.eisi.biosmart.databinding.FragmentFacialBinding

class FacialFragment : Fragment() {

    // 1. Configuramos el View Binding
    private var _binding: FragmentFacialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFacialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Pintamos el "Smart" de morado
        configurarLogoFacial()

        // 3. Programamos el botón "Cancelar" para regresar al Login
        binding.btnCancelarFacial.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun configurarLogoFacial() {
        val texto = "BioSmart"
        val spannable = SpannableString(texto)
        val colorMorado = ContextCompat.getColor(requireContext(), R.color.logo_smart_morado)

        // El color empieza en la posición 3 ('S') y termina en la 8 ('t')
        spannable.setSpan(
            ForegroundColorSpan(colorMorado),
            3,
            8,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvLogoFacial.text = spannable
    }

    // 4. Limpiamos el binding cuando el fragmento se destruye
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}