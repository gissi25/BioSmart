package sv.ues.fia.eisi.biosmart.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import sv.ues.fia.eisi.biosmart.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        navView.setupWithNavController(navController)

        // --- LÓGICA PARA OCULTAR/MOSTRAR EL MENÚ ---
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                // Aquí pones todos los fragmentos donde NO quieres el menú
                R.id.loginFragment,
                R.id.facialFragment,
                R.id.huellaFragment,
                R.id.correoFragment -> {
                    navView.visibility = View.GONE
                }
                // Aquí lo mostramos (Dashboard y otros de monitoreo)
                R.id.dashboardFragment -> {
                    navView.visibility = View.VISIBLE
                }
                // Por defecto, si quieres que se vea en los demás, déjalo Visible
                else -> navView.visibility = View.VISIBLE
            }
        }
    }
}