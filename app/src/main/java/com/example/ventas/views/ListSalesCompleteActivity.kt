package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.adapters.SalesCompleteAdapter
import com.example.ventas.databinding.ActivityListSalesCompleteBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.ProjectDatabase


class ListSalesCompleteActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityListSalesCompleteBinding
    private lateinit var db: ProjectDatabase
    private lateinit var adapter: SalesCompleteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSalesCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewReturnLstSalesCompleteReg.setOnClickListener(this)

        db = Globals.getdataBase(this)!!

        // Obtener el ID y el rol del usuario actual usando Globals
        val currentUserId = Globals.getUserId(this)
        val currentUserRole = Globals.getUserRole(this)

        // Obtener las ventas con detalles y nombres de productos
        val sales = db.saleDao().getAllSalesWithDetails()

        // Filtrar las ventas según el rol del usuario
        val userSales = if (currentUserRole == "1") {
            // Si el usuario es administrador, mostrar todas las ventas
            sales
        } else {
            // Si el usuario no es administrador, mostrar solo sus ventas
            sales.filter { saleWithDetails ->
                saleWithDetails.user.idUsuario == currentUserId
            }
        }

        // Mostrar un Toast con el número de ventas del usuario
        val salesCount = userSales.size
        Toast.makeText(this, "Tienes $salesCount ventas registradas", Toast.LENGTH_SHORT).show()

        // Obtener una instancia de ProductDao
        val productDao = db.productDao()

        // Pasar las ventas filtradas al adaptador
        adapter = SalesCompleteAdapter(this, userSales, productDao)
        binding.lstSalesCompleteReg.adapter = adapter
    }

    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.viewReturnLstSalesCompleteReg -> {
                val intent = Intent(this, SalesActivity::class.java)
                startActivity(intent)
            }
        }
    }
}