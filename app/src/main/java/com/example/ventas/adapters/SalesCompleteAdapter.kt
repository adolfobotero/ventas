package com.example.ventas.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.ventas.R
import com.example.ventas.models.database.dao.ProductDao
import com.example.ventas.models.database.dao.SaleDao

class SalesCompleteAdapter(
    context: Context,
    private val sales: List<SaleDao.SaleCompleteWithDetails>,
    private val productDao: ProductDao
) : ArrayAdapter<SaleDao.SaleCompleteWithDetails>(context, 0, sales) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.adapter_item_sale_complete, parent, false)
        val saleWithDetails = sales[position]

        view.findViewById<TextView>(R.id.tvFechaVentaComplet).text = "Fecha: ${saleWithDetails.sale.fechaVenta}"
        view.findViewById<TextView>(R.id.tvNameClientComplet).text = "Cliente: ${saleWithDetails.customer.nombreCliente}"
        view.findViewById<TextView>(R.id.tvNameSellerComplet).text = "Vendedor: ${saleWithDetails.user.nombreUsuario}"


        val productListView = view.findViewById<ListView>(R.id.productListView)
        val productAdapter = ProductDetailAdapter(context, saleWithDetails.saleDetails, productDao)
        productListView.adapter = productAdapter

        // Calcular el total de la venta
        val totalSaleAmount = saleWithDetails.saleDetails.sumOf { saleDetailWithProduct ->
            saleDetailWithProduct.totalProducto
        }

        // Mostrar el total de la venta
        val tvTotalSale = view.findViewById<TextView>(R.id.tvTotalSale)
        tvTotalSale.text = String.format("Total de la venta: %,.2f", totalSaleAmount)

        return view
    }
}


