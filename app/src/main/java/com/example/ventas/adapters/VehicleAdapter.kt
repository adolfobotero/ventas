package com.example.ventas.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.ventas.R
import com.example.ventas.models.Vehicles
import java.text.NumberFormat
import java.util.Locale

class VehicleAdapter(
    var context: Context,
    var vehicles: List<Vehicles.VehicleWithProduct>
) : BaseAdapter() {

    override fun getCount(): Int {
        return vehicles.size
    }

    override fun getItem(position: Int): Any {
        return vehicles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = convertView
        if (view == null) {
            view = View.inflate(context, R.layout.adapter_list_vehicle, null)
        }

        val vehicle = vehicles[position]

        val txtIdVehicle = view?.findViewById<TextView>(R.id.viewIdVehicle)
        txtIdVehicle?.text = "ID: ${vehicle.idVehiculo}"

        val txtIdProductVeh = view?.findViewById<TextView>(R.id.viewIdProductVehicle)
        txtIdProductVeh?.text = "Code: ${vehicle.idProducto}"

        val txtNameProductVeh = view?.findViewById<TextView>(R.id.viewNameProductVehicle)
        txtNameProductVeh?.text = vehicle.nombreProducto

        val txtTipoVehicle = view?.findViewById<TextView>(R.id.viewTipoVehicle)
        txtTipoVehicle?.text = vehicle.tipoVehiculo

        val txtModelVehicle = view?.findViewById<TextView>(R.id.viewModelVehicle)
        txtModelVehicle?.text = "Model: ${vehicle.modeloVehiculo}"

        val txtColorVehicle = view?.findViewById<TextView>(R.id.viewColorVehicle)
        txtColorVehicle?.text = "Color: ${vehicle.colorVehiculo}"

        val txtPriceProductVeh = view?.findViewById<TextView>(R.id.viewPriceProductVehicle)
        val formattedPrice =
            NumberFormat.getCurrencyInstance(Locale("es", "US")).format(vehicle.valorProducto)
        txtPriceProductVeh?.text = "Price: $formattedPrice"

        val imgViewVehicle = view?.findViewById<ImageView>(R.id.imageViewProductVehicle)
        Glide.with(context).load(vehicle.fotoVehiculo).into(imgViewVehicle!!)

        return view
    }
}