package com.example.ventas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.SelectedProduct
import java.text.NumberFormat
import java.util.Locale

class SelectedProductAdapter(
    context: Context,
    private val products: MutableList<SelectedProduct>
) : ArrayAdapter<SelectedProduct>(context, 0, products) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_item_selected_product, parent, false)
        val product = products[position]

        val productNameTextView = view.findViewById<TextView>(R.id.tvProductNameSale)
        val unitPriceTextView = view.findViewById<TextView>(R.id.tvUnitPriceSale)
        val quantityTextView = view.findViewById<TextView>(R.id.tvQuantitySale)
        val totalTextView = view.findViewById<TextView>(R.id.tvTotalSale)

        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        productNameTextView.text = product.name
        unitPriceTextView.text = numberFormat.format(product.unitPrice)
        quantityTextView.text = product.quantity.toString()
        totalTextView.text = numberFormat.format(product.total)

        return view
    }
}