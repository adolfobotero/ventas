package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.database.entities.CustomerEntity
import com.example.ventas.models.database.entities.SaleDetailEntity
import com.example.ventas.models.database.entities.SaleEntity
import com.example.ventas.models.database.entities.UserEntity

@Dao
interface SaleDao {
    @Insert
    fun insertSale(sale: SaleEntity): Long

    @Delete
    fun deleteSale(sale: SaleEntity)

    @Update
    fun updateSale(sale: SaleEntity)

    @Query("SELECT * FROM tbVentas")
    fun getAllSales(): List<SaleEntity>

    @Transaction
    @Query("SELECT * FROM tbVentas")
    fun getAllSalesWithDetails(): List<SaleCompleteWithDetails>

    data class SaleCompleteWithDetails(
        @Embedded val sale: SaleEntity,
        @Relation(
            parentColumn = "idVenta",
            entityColumn = "idVenta"
        )
        val saleDetails: List<SaleDetailEntity>,

        @Relation(
            parentColumn = "idCliente",
            entityColumn = "idCliente"
        )
        val customer: CustomerEntity,

        @Relation(
            parentColumn = "idUsuario",
            entityColumn = "idUsuario"
        )
        val user: UserEntity
    )
}
