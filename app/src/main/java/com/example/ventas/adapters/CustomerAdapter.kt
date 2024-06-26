package com.example.ventas.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.Customers

class CustomerAdapter: BaseAdapter {
    var context: Context
    var customer: Customers

    constructor(context : Context, customers: Customers){
        this.context = context
        this.customer = customers
    }
    override fun getCount(): Int {
        return customer.customers.size
    }
    override fun getItem(p0: Int): Any {
        return customer.customers[p0]
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var view : View? = p1
        if (view == null){
            view = View.inflate(context, R.layout.adapter_list_customer, null)
        }
        var customer = customer.customers[p0]

        //var txtIdUser = view?.findViewById<TextView>(R.id.viewIdUser)
        //txtIdUser?.text = user.idUsuario.toString()
        var txtCedClient = view?.findViewById<TextView>(R.id.viewIdCardClient)
        txtCedClient?.text = customer.cedulaCliente
        var txtNameClient = view?.findViewById<TextView>(R.id.viewNameClient)
        txtNameClient?.text = customer.nombreCliente
        var txtPhoneClient = view?.findViewById<TextView>(R.id.viewPhoneClient)
        txtPhoneClient?.text = customer.telefonoCliente
        var txtEmailClient = view?.findViewById<TextView>(R.id.viewEmailClient)
        txtEmailClient?.text = customer.correoCliente
        var txtCityClient = view?.findViewById<TextView>(R.id.viewCityClient)
        txtCityClient?.text = customer.ciudadCliente
        return view
    }
}