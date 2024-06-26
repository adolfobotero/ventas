package com.example.ventas.models

import com.example.ventas.models.database.entities.SupplieEntity

class Supplies {
    var supplies = ArrayList<SupplieEntity>()

    constructor(supplies: ArrayList<SupplieEntity>){
        this.supplies = supplies
    }

    data class SuppliesWithProduct(
        val idInsumo: Int,
        val idProducto: Int,
        var marcaInsumo: String,
        var referenciaInsumo: String,
        var fotoInsumo: String = "",
        val nombreProducto: String,
        val valorProducto: Double
    )
}