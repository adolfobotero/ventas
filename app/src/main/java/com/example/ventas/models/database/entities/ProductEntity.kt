package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbProductos")
class ProductEntity {
    @PrimaryKey(autoGenerate = false)
    var idProducto: Int = 0 // No es Autoincremental
    var nombreProducto: String = ""
    var valorProducto: Double = 0.0
}