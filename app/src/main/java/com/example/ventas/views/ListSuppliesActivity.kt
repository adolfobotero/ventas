package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.adapters.SuppliesAdapter
import com.example.ventas.databinding.ActivityListSuppliesBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.ProjectDatabase

class ListSuppliesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityListSuppliesBinding
    lateinit var db: ProjectDatabase
    lateinit var adapter: SuppliesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSuppliesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewReturnLstSuppliesReg.setOnClickListener(this)
        db = Globals.getdataBase(this)!!

        // Obtener la lista de insumos con productos
        val supplies = db.supplieDao().getAllSuppliesWithProduct()

        // Mostrar un Toast con el total de insumos registrados
        Toast.makeText(this, "Total insumos registrados: ${supplies.size}", Toast.LENGTH_LONG)
            .show()

        // Configurar el adaptador
        adapter = SuppliesAdapter(this, supplies)
        binding.lstSuppliesReg.adapter = adapter
    }

    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.viewReturnLstSuppliesReg -> {
                val intent = Intent(this, SuppliesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}