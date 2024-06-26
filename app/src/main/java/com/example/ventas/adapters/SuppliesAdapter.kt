package com.example.ventas.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.ventas.R
import com.example.ventas.models.Supplies
import java.text.NumberFormat
import java.util.Locale

class SuppliesAdapter(
    var context: Context,
    var supplies: List<Supplies.SuppliesWithProduct>
) : BaseAdapter() {

    override fun getCount(): Int {
        return supplies.size
    }

    override fun getItem(position: Int): Any {
        return supplies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View? = convertView
        if (view == null) {
            view = View.inflate(context, R.layout.adapter_list_supplie, null)
        }

        val supplie = supplies[position]

        val txtIdSupplie = view?.findViewById<TextView>(R.id.viewIdSupplie)
        txtIdSupplie?.text = "ID: ${supplie.idInsumo}"

        val txtIdProductSupplie = view?.findViewById<TextView>(R.id.viewIdProductSupplie)
        txtIdProductSupplie?.text = "Code: ${supplie.idProducto}"

        val txtNameProductSupplie = view?.findViewById<TextView>(R.id.viewNameProductSupplie)
        txtNameProductSupplie?.text = supplie.nombreProducto

        val txtBrandSupplie = view?.findViewById<TextView>(R.id.viewBrandSupplie)
        txtBrandSupplie?.text = supplie.marcaInsumo

        val txtInputSupplie = view?.findViewById<TextView>(R.id.viewRefSupplie)
        txtInputSupplie?.text = "Ref: ${supplie.referenciaInsumo}"

        val txtPriceProductSupplie = view?.findViewById<TextView>(R.id.viewPriceProductSupplie)
        val formattedPrice =
            NumberFormat.getCurrencyInstance(Locale("es", "US")).format(supplie.valorProducto)
        txtPriceProductSupplie?.text = "Price: $formattedPrice"

        val imgViewSupplie = view?.findViewById<ImageView>(R.id.imageViewProductSupplie)
        Glide.with(context).load(supplie.fotoInsumo).transform(CircleCrop()).into(imgViewSupplie!!)

        return view
    }
}