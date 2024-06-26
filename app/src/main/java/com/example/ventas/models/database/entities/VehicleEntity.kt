package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "tbVehiculos",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["idProducto"],
            childColumns = ["idProducto"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class VehicleEntity {
    @PrimaryKey(autoGenerate = true)
    var idVehiculo: Int = 0 // Autoincremental
    var idProducto: Int = 0 // Referencia al idProducto de la tabla tbProductos
    var tipoVehiculo: String = ""
    var modeloVehiculo: Int = 0
    var colorVehiculo: String = ""
    var fotoVehiculo: String = ""
}