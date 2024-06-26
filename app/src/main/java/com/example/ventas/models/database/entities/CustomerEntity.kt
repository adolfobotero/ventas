package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tbClientes", indices = [Index(value = ["cedulaCliente"], unique = true)])
class CustomerEntity {
    @PrimaryKey(autoGenerate = true)
    var idCliente: Int = 0 // Autoincremental

    var cedulaCliente: String = ""
    var nombreCliente: String = ""
    var telefonoCliente: String = ""
    var correoCliente: String = ""
    var ciudadCliente: String = ""
}