package com.example.ventas.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ventas.R
import com.example.ventas.databinding.ActivityCustomerBinding
import com.example.ventas.models.Globals
import com.example.ventas.models.database.entities.CustomerEntity

class CustomerActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCreateClient.setOnClickListener(this)
        binding.btnListClients.setOnClickListener(this)
        binding.viewReturnCustomer.setOnClickListener(this)
    }
    override fun onClick(v0: View?) {
        when(v0?.id){
            R.id.btnCreateClient -> {
                val idCard = binding.txtIdCardCustomer.editText?.text.toString()
                val name = binding.txtNameCustomer.editText?.text.toString()
                val phone = binding.txtPhoneCustomer.editText?.text.toString()
                val email = binding.txtEmailCustomer.editText?.text.toString()
                val city = binding.txtCityCustomer.editText?.text.toString()

                if (idCard.isEmpty() || name.isEmpty() || phone.isEmpty() || email.isEmpty() || city.isEmpty()) {
                    // Si alguno de los campos está vacío, mostrar un mensaje de error
                    Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                } else if (!isValidEmail(email)) {
                    // Si el formato del correo electrónico no es válido, mostrar un mensaje de error
                    Toast.makeText(this, "Por favor ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
                } else {
                    // Verificar si ya existe un cliente con el mismo código
                    val existingCustomer =
                        Globals.getdataBase(this)?.customerDao()?.getCustomerByCedula(idCard)
                    if (existingCustomer != null) {
                        // Si ya existe un cliente con el mismo código, mostrar un mensaje de error
                        Toast.makeText(
                            this,
                            "Ya existe un cliente con esta cédula",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Todos los campos estan OK
                        val customerEntity = CustomerEntity()
                        customerEntity.cedulaCliente = idCard
                        customerEntity.nombreCliente = name
                        customerEntity.telefonoCliente = phone
                        customerEntity.correoCliente = email
                        customerEntity.ciudadCliente = city

                        Globals.getdataBase(this)?.customerDao()?.insertCustomer(customerEntity)
                        Toast.makeText(this, "Se ha agregado un cliente", Toast.LENGTH_LONG).show()
                        clearFields()
                    }
                }
            }
            R.id.btnListClients -> {
                val intent = Intent(this, ListCustomersActivity::class.java)
                startActivity(intent)
            }

            R.id.viewReturnCustomer -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun clearFields() {
        binding.txtIdCardCustomer.editText?.setText("")
        binding.txtNameCustomer.editText?.setText("")
        binding.txtPhoneCustomer.editText?.setText("")
        binding.txtEmailCustomer.editText?.setText("")
        binding.txtCityCustomer.editText?.setText("")
    }
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

}