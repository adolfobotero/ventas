package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.adapters.VehicleAdapter
import com.example.ventas.databinding.ActivityListVehiclesBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.ProjectDatabase

class ListVehiclesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityListVehiclesBinding
    lateinit var db: ProjectDatabase
    lateinit var adapter: VehicleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListVehiclesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewReturnLstVehiclesReg.setOnClickListener(this)
        db = Globals.getdataBase(this)!!

        // Obtener la lista de vehículos con productos
        val vehicles = db.vehicleDao().getAllVehiclesWithProduct()

        // Mostrar un Toast con el total de vehículos registrados
        Toast.makeText(this, "Total vehículos registrados: ${vehicles.size}", Toast.LENGTH_LONG)
            .show()

        // Configurar el adaptador
        adapter = VehicleAdapter(this, vehicles)
        binding.lstVehiclesReg.adapter = adapter

    }

    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.viewReturnLstVehiclesReg -> {
                val intent = Intent(this, VehiclesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
