package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.adapters.CustomerAdapter
import com.example.ventas.databinding.ActivityListCustomersBinding
import com.example.ventas.models.Customers
import com.example.ventas.models.Globals
import com.example.ventas.models.database.entities.CustomerEntity

class ListCustomersActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityListCustomersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewReturnLstClientsReg.setOnClickListener(this)

        var customers : Customers = Customers(Globals.getdataBase(this)?.customerDao()?.getAllCustomers()!! as ArrayList<CustomerEntity>)
        var adapter : CustomerAdapter = CustomerAdapter(this, customers)
        binding.lstClientsReg.adapter = adapter

        Toast.makeText(this, "Total clientes registrados: ${customers.customers.size}", Toast.LENGTH_LONG).show()
    }

    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.viewReturnLstClientsReg -> {
                val intent = Intent(this, CustomerActivity::class.java)
                startActivity(intent)
            }
        }
    }
}