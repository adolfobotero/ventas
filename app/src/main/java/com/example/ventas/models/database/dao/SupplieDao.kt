package com.example.ventas.models.database.dao

import androidx.room.*
import com.example.ventas.models.Supplies
import com.example.ventas.models.database.entities.SupplieEntity

@Dao
interface SupplieDao {
    @Insert
    fun insertSupplie(supplie: SupplieEntity)

    @Delete
    fun deleteSupplie(supplie: SupplieEntity)

    @Update
    fun updateSupplie(supplie: SupplieEntity)

    @Query(
        """
        SELECT s.idInsumo, s.idProducto, s.marcaInsumo, s.referenciaInsumo, s.fotoInsumo, p.nombreProducto, p.valorProducto
        FROM tbInsumos s
        INNER JOIN tbProductos p ON s.idProducto = p.idProducto
    """
    )
    fun getAllSuppliesWithProduct(): List<Supplies.SuppliesWithProduct>

    @Query("SELECT * FROM tbInsumos")
    fun getAllSupplies(): List<SupplieEntity>
}