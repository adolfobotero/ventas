package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.database.entities.SaleDetailEntity

@Dao
interface SaleDetailDao {
    @Insert
    fun insertSaleDetail(saleDetail: SaleDetailEntity)

    @Delete
    fun deleteSaleDetail(saleDetail: SaleDetailEntity)

    @Update
    fun updateSaleDetail(saleDetail: SaleDetailEntity)

    @Query("SELECT * FROM tbDetalleVentas WHERE idDetalleVentas = :detaVentasId")
    fun getDetaVentasById(detaVentasId: Int): SaleDetailEntity?

    @Query("SELECT * FROM tbDetalleVentas")
    fun getAllSalesDetail(): List<SaleDetailEntity>
}