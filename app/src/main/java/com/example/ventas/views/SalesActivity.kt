package com.example.ventas.views

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ventas.R
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.adapters.ClientAdapter
import com.example.ventas.adapters.ProductAdapter
import com.example.ventas.adapters.SelectedProductAdapter
import com.example.ventas.databinding.ActivitySalesBinding
import com.example.ventas.models.Customer
import com.example.ventas.models.Globals
import com.example.ventas.models.Product
import com.example.ventas.models.SelectedProduct
import com.example.ventas.models.database.ProjectDatabase
import com.example.ventas.models.database.entities.SaleDetailEntity
import com.example.ventas.models.database.entities.SaleEntity
import java.text.NumberFormat
import java.util.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class SalesActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySalesBinding
    private lateinit var editTextDate: EditText
    private lateinit var adapter: SelectedProductAdapter
    private lateinit var db: ProjectDatabase

    private val selectedProducts = mutableListOf<SelectedProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnListSales.setOnClickListener(this)
        binding.viewReturnSale.setOnClickListener(this)

        // Obtener la base de datos a través de Globals
        db = Globals.getdataBase(this)!!

        // Obtener clientes y productos de la base de datos
        val clientes = db.customerDao().getAllCustomers().map { customerEntity ->
            Customer(customerEntity.nombreCliente, customerEntity.cedulaCliente)
        }
        val productos = db.productDao().getAllProducts().map { productEntity ->
            Product(
                productEntity.idProducto,
                productEntity.nombreProducto,
                productEntity.valorProducto
            )
        }

        // Inicializar views y listeners
        binding.btnAddProduct.setOnClickListener(this)
        editTextDate = findViewById(R.id.txtDateSale)

        // Configurar el DatePicker para el campo de fecha de venta
        editTextDate.setOnClickListener { showDatePickerDialog() }

        // Establecer la fecha actual como predeterminada
        val currentDate = LocalDate.now()
        editTextDate.setText(currentDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")))

        // Desplegar los productos
        ProductAdapter(this, productos).also {
            binding.txtProductNameSale.setAdapter(it)
        }

        // Desplegar los clientes
        ClientAdapter(this, clientes).also {
            binding.txtCustomerSale.setAdapter(it)
        }

        // Inicializar adaptador para productos seleccionados
        adapter = SelectedProductAdapter(this, selectedProducts)
        binding.lstAddProducts.adapter = adapter

        // Configurar OnClickListener para el botón "Guardar Venta"
        binding.btnSaveSale.setOnClickListener {
            saveSaleToDatabase()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "${dayOfMonth}/${monthOfYear + 1}/${year}"
                editTextDate.setText(selectedDate)
            },
            year,
            month,
            day
        )

        // Establecer la fecha mínima como la fecha actual
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAddProduct -> {
                // Agregar producto a la lista
                val productName = binding.txtProductNameSale.text.toString()
                val quantityText = binding.txtProductQuantity.editText?.text.toString()

                if (productName.isEmpty() || quantityText.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Debe seleccionar un producto y la cantidad",
                        Toast.LENGTH_LONG
                    ).show()
                    return
                }

                val quantity = quantityText.toInt()
                val product = db.productDao().getAllProducts().find {
                    it.nombreProducto == productName
                }

                product?.let {
                    val unitPrice = it.valorProducto
                    val total = unitPrice * quantity

                    selectedProducts.add(SelectedProduct(productName, unitPrice, quantity, total))
                    adapter.notifyDataSetChanged()

                    val totalPurchase = selectedProducts.sumOf { it.total }
                    val formattedTotal =
                        NumberFormat.getNumberInstance(Locale.US).format(totalPurchase)
                    binding.txtTotalVenta.text = "Total: $ $formattedTotal"
                }

                clearFieldsAdd()
            }

            R.id.btnListSales -> {
                val intent = Intent(this, ListSalesCompleteActivity::class.java)
                startActivity(intent)
            }

            R.id.viewReturnSale -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun clearFieldsAdd() {
        binding.txtProductNameSale.setText("")
        binding.txtProductQuantity.editText?.setText("")
    }

    private fun saveSaleToDatabase() {

        // Validar que la fecha no esté vacía
        val saleDateText = binding.txtDateSale.text.toString()
        if (saleDateText.isEmpty()) {
            Toast.makeText(this, "La fecha de venta no puede estar vacía", Toast.LENGTH_LONG).show()
            return
        }

        // Obtener el cliente seleccionado
        val customerName = binding.txtCustomerSale.text.toString()
        val customer = db.customerDao().getAllCustomers().find { it.nombreCliente == customerName }

        if (customer == null) {
            Toast.makeText(this, "Cliente no encontrado", Toast.LENGTH_LONG).show()
            return
        }

        // Validar que existan productos agregados
        if (selectedProducts.isEmpty()) {
            Toast.makeText(this, "Debe agregar al menos un producto", Toast.LENGTH_LONG).show()
            return
        }

        // Obtener el ID del vendedor desde SharedPreferences
        val userId = Globals.getUserId(this)
        if (userId == -1) {
            Toast.makeText(this, "Usuario no válido", Toast.LENGTH_LONG).show()
            return
        }

        // Obtener y verificar la fecha de venta
        val saleDate = try {
            LocalDate.parse(
                binding.txtDateSale.text.toString(),
                DateTimeFormatter.ofPattern("d/M/yyyy")
            )
        } catch (e: Exception) {
            Toast.makeText(this, "Fecha de venta no válida", Toast.LENGTH_LONG).show()
            return
        }

        // Crear y guardar la entidad de venta
        val sale = SaleEntity().apply {
            idCliente = customer.idCliente
            idUsuario = userId
            fechaVenta = saleDate
        }

        // Obtener el ID de la venta insertada
        val saleId = db.saleDao().insertSale(sale).toInt()

        // Crear y guardar los detalles de la venta
        selectedProducts.forEach {
            val product = db.productDao().getAllProducts().find { product ->
                product.nombreProducto == it.name
            }

            if (product != null) {
                val saleDetail = SaleDetailEntity().apply {
                    idVenta = saleId
                    idProducto = product.idProducto
                    cantidadProducto = it.quantity
                    totalProducto = it.total
                }

                db.saleDetailDao().insertSaleDetail(saleDetail)
            } else {
                Toast.makeText(this, "Producto no encontrado: ${it.name}", Toast.LENGTH_LONG).show()
            }
        }

        // Mostrar un mensaje de éxito y limpiar la lista de productos seleccionados
        Toast.makeText(this, "Venta registrada exitosamente", Toast.LENGTH_LONG).show()
        selectedProducts.clear()
        adapter.notifyDataSetChanged()
        binding.txtTotalVenta.text = "Total: $0.0"

        clearFieldsSale()
    }

    private fun clearFieldsSale() {
        binding.txtDateSale.setText("")
        binding.txtCustomerSale.setText("")
        binding.txtProductNameSale.setText("")
        binding.txtProductQuantity.editText?.setText("")
    }
}