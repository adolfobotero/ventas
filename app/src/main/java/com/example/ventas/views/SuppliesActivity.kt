package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.databinding.ActivitySuppliesBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.entities.ProductEntity
import com.example.ventas.models.database.entities.SupplieEntity

class SuppliesActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivitySuppliesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuppliesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCreateSupplie.setOnClickListener(this)
        binding.btnListSupplie.setOnClickListener(this)
        binding.viewReturnSupplie.setOnClickListener(this)
    }

    override fun onClick(v0: View?) {
        when (v0?.id) {
            R.id.btnCreateSupplie -> {

                val codeProduct = binding.txtCodeProdSupplie.editText?.text.toString().toIntOrNull() ?: 0
                val nameProduct = binding.txtNameProdSupplie.editText?.text.toString()
                val priceProductText = binding.txtPriceProdSupplie.editText?.text.toString()
                val brandSupplie = binding.txtBrandSupplie.editText?.text.toString()
                val inputSupplie = binding.txtInputSupplie.editText?.text.toString()
                val pictureSupplie = binding.txtPictureSupplie.editText?.text.toString()

                if (codeProduct == 0 || nameProduct.isEmpty() || brandSupplie.isEmpty() || inputSupplie.isEmpty() || priceProductText.isEmpty()) {
                    // Si alguno de los campos está vacío, mostrar un mensaje de error
                    Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
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
                            Toast.makeText(
                                this,
                                "Ya existe un producto con este código",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // Se crea primero el producto padre
                            val productEntity = ProductEntity()
                            productEntity.idProducto = codeProduct
                            productEntity.nombreProducto = nameProduct
                            productEntity.valorProducto = priceProduct
                            Globals.getdataBase(this)?.productDao()?.insertProduct(productEntity)

                            // Despues se crea el producto hijo
                            val supplieEntity = SupplieEntity()
                            supplieEntity.idProducto = codeProduct
                            supplieEntity.marcaInsumo = brandSupplie
                            supplieEntity.referenciaInsumo = inputSupplie
                            supplieEntity.fotoInsumo = pictureSupplie

                            Globals.getdataBase(this)?.supplieDao()?.insertSupplie(supplieEntity)
                            Toast.makeText(this, "Se ha agregado un insumo", Toast.LENGTH_LONG).show()
                            clearFields()
                        }
                    }
                }
            }
            R.id.btnListSupplie -> {
                val intent = Intent(this, ListSuppliesActivity::class.java)
                startActivity(intent)
            }
            R.id.viewReturnSupplie -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun clearFields() {
        binding.txtCodeProdSupplie.editText?.setText("")
        binding.txtNameProdSupplie.editText?.setText("")
        binding.txtBrandSupplie.editText?.setText("")
        binding.txtInputSupplie.editText?.setText("")
        binding.txtPriceProdSupplie.editText?.setText("")
        binding.txtPictureSupplie.editText?.setText("")
    }
}