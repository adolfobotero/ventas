package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "tbInsumos",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["idProducto"],
            childColumns = ["idProducto"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

class SupplieEntity {
    @PrimaryKey(autoGenerate = true)
    var idInsumo: Int = 0 // Autoincremental
    var idProducto: Int = 0 // Referencia al idProducto de la tabla tbProductos
    var marcaInsumo: String = ""
    var referenciaInsumo: String = ""
    var fotoInsumo: String = ""
}