package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.databinding.ActivityMenuBinding
import com.example.ventas.models.Globals

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Establecer el listener de clic para los botones
        binding.btnCustomers.setOnClickListener(this)
        binding.btnVehicles.setOnClickListener(this)
        binding.btnSupplies.setOnClickListener(this)
        binding.btnSales.setOnClickListener(this)
        binding.btnListUsers.setOnClickListener(this)

        // Obtener el nombre de usuario guardado en SharedPreferences
        val username = Globals.getUserName(this)
        // Mostrar el nombre de usuario en un TextView
        binding.viewUserConnected.text = "Usuario conectado: $username"

        // Cerrar la sesión del usuario
        binding.viewLogOut.setOnClickListener(this)

        //Ocultar botón Listar Usuarios según Rol de usuario recuperando el rol del usuario desde SharedPreferences
        val userRole = Globals.getUserRole(this)
        // Verificar si el rol del usuario es 1 para mostrar u ocultar el botón btnListUsuarios
        val btnListUsuarios = findViewById<Button>(R.id.btnListUsers)
        btnListUsuarios.visibility = if (userRole == "1") View.VISIBLE else View.GONE
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnCustomers -> {
                val intent = Intent(this, CustomerActivity::class.java)
                startActivity(intent)
            }
            R.id.btnVehicles -> {
                val intent = Intent(this, VehiclesActivity::class.java)
                startActivity(intent)
            }
            R.id.btnSupplies -> {
                val intent = Intent(this, SuppliesActivity::class.java)
                startActivity(intent)
            }
            R.id.btnSales -> {
                val intent = Intent(this, SalesActivity::class.java)
                startActivity(intent)
            }
            R.id.btnListUsers -> {
                val intent = Intent(this, ListUsersActivity::class.java)
                startActivity(intent)
            }
            R.id.viewLogOut -> {
                // Borrar el nombre de usuario almacenado en SharedPreferences
                Globals.clearUserCode(this)
                // Redirigir al usuario a la pantalla de inicio de sesión
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}