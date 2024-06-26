package com.example.ventas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.Customer

class ClientAdapter (
    context: Context,
    clientes: List<Customer>
): ArrayAdapter<Customer>(context,0, clientes){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vista = convertView?: LayoutInflater.from(context).inflate(R.layout.adapter_item_customer, parent, false)

        getItem(position)?.let {
            vista.findViewById<TextView>(R.id.tvNomClient).apply{
                text = it.nameCustomer
            }
            vista.findViewById<TextView>(R.id.tvCedClient).apply{
                text = it.ccCustomer
            }
        }
        return vista
    }
}
