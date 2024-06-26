package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.database.entities.CustomerEntity

@Dao
interface CustomerDao {
    @Insert
    fun insertCustomer(customer: CustomerEntity)

    @Delete
    fun deleteCustomer(customer: CustomerEntity)

    @Update
    fun updateCustomer(customer: CustomerEntity)

    @Query("SELECT * FROM tbClientes WHERE idCliente = :customerId")
    fun getCustomerById(customerId: Int): CustomerEntity?

    @Query("SELECT * FROM tbClientes WHERE cedulaCliente = :customerCedula")
    fun getCustomerByCedula(customerCedula: String): CustomerEntity?

    @Query("SELECT * FROM tbClientes")
    fun getAllCustomers(): List<CustomerEntity>
}