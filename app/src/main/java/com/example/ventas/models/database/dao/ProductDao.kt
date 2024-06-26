package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.database.entities.ProductEntity

@Dao
interface ProductDao {
    @Insert
    fun insertProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(product: ProductEntity)

    @Update
    fun updateProduct(product: ProductEntity)

    @Query("SELECT * FROM tbProductos WHERE idProducto = :productId")
    fun getProductById(productId: Int): ProductEntity?

    @Query("SELECT * FROM tbProductos")
    fun getAllProducts(): List<ProductEntity>
}