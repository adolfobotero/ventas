package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import java.time.LocalDate

@Entity(tableName = "tbVentas",
    foreignKeys = [
        ForeignKey(
            entity = CustomerEntity::class,
            parentColumns = ["idCliente"],
            childColumns = ["idCliente"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["idUsuario"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class SaleEntity {
    @PrimaryKey(autoGenerate = true)
    var idVenta: Int = 0 // Autoincremental

    var idCliente: Int = 0 // Referencia al idCliente de la tabla tbClientes
    var idUsuario: Int = 0 // Referencia al idUsuario de la tabla tbUsuarios
    var fechaVenta: LocalDate = LocalDate.now()
}