package com.example.ventas.models.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tbUsuarios", indices = [Index(value = ["cedulaUsuario"], unique = true)])
class UserEntity {
    @PrimaryKey(autoGenerate = true)
    var idUsuario: Int = 0 // Autoincremental

    var cedulaUsuario: String = ""
    var nombreUsuario: String = ""
    var claveUsuario: String = ""
    var rolUsuario: Int = 1
}