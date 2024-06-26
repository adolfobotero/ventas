package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import java.util.Calendar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.databinding.ActivityVehiclesBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.entities.ProductEntity
import com.example.ventas.models.database.entities.VehicleEntity

class VehiclesActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityVehiclesBinding

    // Obtener el año actual
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val nextYear = currentYear + 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehiclesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCreateVehiculo.setOnClickListener(this)
        binding.btnListVehicle.setOnClickListener(this)
        binding.viewReturnVehicle.setOnClickListener(this)
    }
    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.btnCreateVehiculo -> {

                val codeProduct = binding.txtCodeProdVehicle.editText?.text.toString().toIntOrNull() ?: 0
                val nameProduct = binding.txtNameProdVehicle.editText?.text.toString()
                val priceProductText = binding.txtVrProdVehicle.editText?.text.toString()
                val typeVeh = binding.txtTipoVehicle.editText?.text.toString()
                val modelVehString = binding.txtModelVehicle.editText?.text.toString()
                val modelVeh = binding.txtModelVehicle.editText?.text.toString().toIntOrNull() ?: 0
                val colorVeh = binding.txtColorVehicle.editText?.text.toString()
                val pictureVeh = binding.txtPictureVehicle.editText?.text.toString()

                if (codeProduct == 0 || nameProduct.isEmpty() || typeVeh.isEmpty() || modelVeh == 0 || colorVeh.isEmpty() || priceProductText.isEmpty()) {
                    // Si alguno de los campos está vacío, mostrar un mensaje de error
                    Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                } else if (modelVehString.length != 4 || modelVeh > nextYear) {
                    // Validar que modelVeh sea un año válido (4 dígitos) y menor o igual al año actual más 1
                    // Si el año no tiene 4 dígitos, es menor al año actual o mayor al año actual + 1, mostrar un mensaje de error
                    Toast.makeText(this, "El año del modelo debe ser un año válido y menor o igual al año actual + 1", Toast.LENGTH_SHORT).show()
                } else {
                    val priceProduct = priceProductText.toDoubleOrNull()

                    if (priceProduct == null || priceProduct == 0.0) {
                        // Si el campo de precio no es un número válido o es igual a 0.0, mostrar un mensaje de error
                        Toast.makeText(this,"Por favor ingrese un precio válido", Toast.LENGTH_SHORT).show()
                    } else {
                        // Verificar si ya existe un producto con el mismo código
                        val existingProduct =
                            Globals.getdataBase(this)?.productDao()?.getProductById(codeProduct)
                        if (existingProduct != null) {
                            // Si ya existe un producto con el mismo código, mostrar un mensaje de error
                            Toast.makeText(this,"Ya existe un producto con este código", Toast.LENGTH_SHORT).show()
                        } else {
                            // Todos los campos estan OK
                            val productEntity = ProductEntity()
                            productEntity.idProducto = codeProduct
                            productEntity.nombreProducto = nameProduct
                            productEntity.valorProducto = priceProduct
                            Globals.getdataBase(this)?.productDao()?.insertProduct(productEntity)

                            val vehicleEntity = VehicleEntity()
                            vehicleEntity.idProducto = codeProduct
                            vehicleEntity.tipoVehiculo = typeVeh
                            vehicleEntity.modeloVehiculo = modelVeh
                            vehicleEntity.colorVehiculo = colorVeh
                            vehicleEntity.fotoVehiculo = pictureVeh

                            Globals.getdataBase(this)?.vehicleDao()?.insertVehicle(vehicleEntity)
                            Toast.makeText(this, "Se ha agregado un vehículo", Toast.LENGTH_LONG).show()
                            clearFields()
                        }
                    }
                }
            }
            R.id.btnListVehicle -> {
                val intent = Intent(this, ListVehiclesActivity::class.java)
                startActivity(intent)
            }
            R.id.viewReturnVehicle -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun clearFields() {
        binding.txtCodeProdVehicle.editText?.setText("")
        binding.txtNameProdVehicle.editText?.setText("")
        binding.txtTipoVehicle.editText?.setText("")
        binding.txtModelVehicle.editText?.setText("")
        binding.txtColorVehicle.editText?.setText("")
        binding.txtVrProdVehicle.editText?.setText("")
        binding.txtPictureVehicle.editText?.setText("")
    }
}