package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "tbDetalleVentas",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["idVenta"],
            childColumns = ["idVenta"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["idProducto"],
            childColumns = ["idProducto"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class SaleDetailEntity {
    @PrimaryKey(autoGenerate = true)
    var idDetalleVentas: Int = 0 // Autoincremental
    var idVenta: Int = 0 // Referencia al idVenta de la tabla tbVentas
    var idProducto: Int = 0 // Referencia al idProducto de la tabla tbProductos
    var cantidadProducto: Int = 0
    var totalProducto: Double = 0.0
}