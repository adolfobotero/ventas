package com.example.ventas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.Product
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(
    context: Context,
    productos: List<Product>
) : ArrayAdapter<Product>(context, 0, productos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vista = convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_item_product, parent, false)

        getItem(position)?.let { product ->
            vista.findViewById<TextView>(R.id.tvNomProduct).apply {
                text = product.nameProduct
            }
            vista.findViewById<TextView>(R.id.tvPriceProduct).apply {
                val numberFormat = NumberFormat.getNumberInstance(Locale.US)
                text = "$${numberFormat.format(product.priceProduct)}"
            }
        }
        return vista
    }
}