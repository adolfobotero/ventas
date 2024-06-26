package com.example.ventas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.database.dao.ProductDao
import com.example.ventas.models.database.entities.SaleDetailEntity

class ProductDetailAdapter(
    context: Context,
    private val saleDetails: List<SaleDetailEntity>,
    private val productDao: ProductDao
) : ArrayAdapter<SaleDetailEntity>(context, 0, saleDetails) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_item_sale_details, parent, false)
        val saleDetail = saleDetails[position]

        // Obtener el nombre del producto desde la base de datos
        val productName = productDao.getProductById(saleDetail.idProducto)?.nombreProducto ?: "Producto no encontrado"

        view.findViewById<TextView>(R.id.tvProductCodeDetail).text = saleDetail.idProducto.toString()
        view.findViewById<TextView>(R.id.tvProductNameDetail).text = productName
        view.findViewById<TextView>(R.id.tvProductQuantityDetail).text = saleDetail.cantidadProducto.toString()
        view.findViewById<TextView>(R.id.tvProductTotalDetail).text = String.format("%,.2f", saleDetail.totalProducto)

        return view
    }
}
